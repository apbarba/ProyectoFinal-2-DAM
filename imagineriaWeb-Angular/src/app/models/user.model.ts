export class User {

    email: string;
    token?: string;
    refreshToken?: string;

    constructor( email: string, token: string, refreshToke: string){
        this.email = email;
        this.token = token;
        this.refreshToken = refreshToke;
    }
}
