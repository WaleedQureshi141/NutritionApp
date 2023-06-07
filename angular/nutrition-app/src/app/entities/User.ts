import { Role } from "./Role"

export interface User {
    userName: string,
    password: string,
    enabled: boolean,
    roles: Role[]
}