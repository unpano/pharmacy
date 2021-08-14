
import { Pharmacy } from "../dto/pharmacy";
import { Token } from "../dto/token";
import { User } from "../dto/user";


export namespace Global {
    export var loggedUser: User = new User();
    export var token: Token = new Token();
    export var clickedPharmacy: Pharmacy = new Pharmacy();
    export var clickedSignUp: boolean = false;
    export var clickedLogin: boolean = false;
    export var viewUserDetails: User = new User();
    export var mappedUser: boolean = false;
    
    
}