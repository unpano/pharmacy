import { Med } from "./med"
import { Pharmacy } from "./pharmacy"
import { User } from "./user"

export class Reservation{
    id: Number
    pickUpDate: Date
    med: Med
    user: User
    pharmacy: Pharmacy
}