import { Pharmacy } from "./pharmacy";
import { User } from "./user";

export class DermAppointment{
    id: Number
    startTime : String
    endTime : String
    date: Date
    duration: Number
    price: Number
    user: User
    dermatologist: User
    pharmacy: Pharmacy
}