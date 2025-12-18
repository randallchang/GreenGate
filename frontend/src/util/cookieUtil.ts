import Cookies from 'js-cookie'

const TokenKey = 'green_gate_admin_token'

export function getToken(): string {
    return Cookies.get(TokenKey)
}

export function setToken(token): string {
    return Cookies.set(TokenKey, token)
}

export function removeToken(): void {
    return Cookies.remove(TokenKey)
}
