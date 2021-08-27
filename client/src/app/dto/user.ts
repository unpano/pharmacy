import { Category } from "./category";
import { LoyaltyProgram } from "./loyaltyProgram";
import { Pharmacy } from "./pharmacy";

export class User{
    id: Number;
    username: String;
    password: String;
    firstName: String;
    lastName: String;
    email: String;
    phoneNumber: String;
    address: String;
    city: String;
    country: String;
    enabled: Boolean;
    lastPasswordResetDate: Date
    stars: Number
    points: Number
    userCategory: Category
    loyaltyProgram: LoyaltyProgram
    pharmacy: Pharmacy
    penalties: Number
}