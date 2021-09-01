import { Pharmacy } from "./pharmacy";
import { User } from "./user";

export class Vacation{
    id: Number;

   startDate : Date;

    endDate : Date;

    answered : boolean;

    approved : boolean;

    pharmacist : User;

    dermatologist : User;
    
    pharmacy : Pharmacy;


}