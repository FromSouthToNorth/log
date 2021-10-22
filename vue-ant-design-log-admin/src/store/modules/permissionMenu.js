import {constantRoutes} from "@/router";
import {getRouters} from "@/api/menu";
import Layout from "@/layouts/Default";

const permissionMenu = {
    state: {
        routes: [],
        addRoutes: [],
        defaultRoutes: [],
        // topbarRouters: [],
        sidebarRouters: []
    },
    mutations: {
        SET_ROUTES: (state, routes) => {
            state.addRoutes = routes
            state.routes = constantRoutes.concat(routes)
        },
        SET_DEFAULT_ROUTES: (state, routes) => {
            state.defaultRoutes = constantRoutes.concat(routes)
        },
        SET_SIDEBAR_ROUTERS: (state, routes) => {
            state.sidebarRouters = routes
        },
    },
    actions: {
        /** 获取路由 */
        GenerateRoutes({commit}) {
            return new Promise(resolve => {
                getRouters().then(result => {
                    const data = JSON.parse(JSON.stringify(result.data));
                    const rdata = JSON.parse(JSON.stringify(result.data));
                    const sidebarRoutes = filterAsyncRouter(data);
                    const rewriteRoutes = filterAsyncRouter(rdata, false, true);
                    rewriteRoutes.push({path: "*", redirect: "/404", hidden: true});
                    commit("SET_ROUTES", rewriteRoutes);
                    commit('SET_SIDEBAR_ROUTERS', constantRoutes.concat(sidebarRoutes))
                    commit('SET_DEFAULT_ROUTES', sidebarRoutes)
                    resolve(rewriteRoutes);
                });
            });
        },
    }
}

// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter(asyncRouterMap, lastRouter = false, type = false) {
    return asyncRouterMap.filter(route => {
        if (type && route.children) {
            route.children = filterChildren(route.children);
        }
        if (route.component) {
            if (route.component === "Layout") {
                route.component = Layout;
            } else {
                route.component = loadView(route.component);
            }
        }
        if (route.children && route.children.length) {
            route.children = filterAsyncRouter(route.children, route, type);
        } else {
            delete route['children'];
            delete route['redirect']
        }
        return true;
    })
}

function filterChildren(childrenMap, lastRouter = false) {
    var children = [];
    childrenMap.forEach((element, index) => {
        if (element.children && element.children.length) {
            if (element.component === "ParentView" && !lastRouter) {
                element.children.forEach(el => {
                    el.path = element.path + "/" + el.path;
                    if (el.children && el.children.length) {
                        children = children.concat(filterChildren(el.children, el));
                        return;
                    }
                    children.push(el);
                });
                return;
            }
        }
        if (lastRouter) {
            element.path = lastRouter.path + "/" + element.path;
        }
        children = children.concat(element);
    });
    return children;
}

export const loadView = (view) => {
    if (process.env.NODE_ENV === 'development') {
        return (resolve) => require([`@/views/${view}`], resolve)
    } else {
        // 使用 import 实现生产环境的路由懒加载
        return () => import(`@/views/${view}`)
    }
}

export default permissionMenu;