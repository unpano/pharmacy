import { User } from "../dto/user";


export namespace Global {
    export var loggedUser: User = new User();
    export var viewUserDetails: User = new User();
    export var mappedUser: boolean = false;
    export var clickedSignUp: boolean = false;
}