const getters = {
    roles: state => state.user.roles,
    sidebarRouters: state => state.permissionMenu.sidebarRouters
}

export default getters;