
import { Med } from "../dto/med";
import { Order } from "../dto/order";
import { Pharmacy } from "../dto/pharmacy";
import { Promotion } from "../dto/promotion";
import { Token } from "../dto/token";
import { User } from "../dto/user";


export namespace Global {
    export var loggedUser: User = new User();
    export var order: Order = new Order();
    export var clickedOrder: Order = new Order();
    export var token: Token = new Token();
    export var clickedPharmacy: Pharmacy = new Pharmacy();
    export var clickedSignUp: boolean = false;
    export var clickedLogin: boolean = false;
    export var viewUserDetails: User = new User();
    export var mappedUser: boolean = false;
    export var clickedMed: Med = new Med();
    export var allMeds: boolean = false;
    export var reserveFromPickedPharmacy: boolean = false;
    export var medToReserve: Med = new Med();
    export var lastPromotion: Promotion= new Promotion();
}