import { Med } from "./med"
import { PrescriptionStatus } from "./prescriptionStatus"
import { User } from "./user"

export class Prescription{
    id: Number
    date: Date
    user: User
    meds: Med[]
    prescriptionStatus: PrescriptionStatus
}