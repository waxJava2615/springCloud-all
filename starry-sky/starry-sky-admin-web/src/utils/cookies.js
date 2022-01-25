import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

// export function getToken() {
//   return Cookies.get(TokenKey)
// }
//
// export function setToken(token) {
//   return Cookies.set(TokenKey, token)
// }
//
// export function removeToken() {
//   return Cookies.remove(TokenKey)
// }


const cookieApi = {
  getToken() {
    return Cookies.get(TokenKey)
  },
  setToken(token,expires) {
    return Cookies.set(TokenKey, token,{expires:1})
  },
  removeToken() {
    return Cookies.remove(TokenKey)
  }
}
