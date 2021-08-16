import { Form } from "./form";
import { IssuanceRegime } from "./issuanceRegime";

export class Med{
    id: Number;
    name: String;
    type: String;
    form: Form;
    producer: String;
    issuanceRegime: IssuanceRegime;
    additionalNotes: String;
    price: Number;
}