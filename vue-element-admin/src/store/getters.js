// vuex统一暴露
const getters = {
  sidebar: state => state.app.sidebar,
  language: state => state.app.language,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  userInfo: state => state.user.userInfo,
  permission: state => state.user.permission,
  setting: state => state.user.setting,
  errorLogs: state => state.errorLog.logs
}
export default getters
