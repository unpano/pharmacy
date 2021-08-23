import { Med } from "./med"
import { User } from "./user"

export class Reservation{
    id: Number
    pickUpDate: Date
    med: Med
    user: User
}