import { FormControl } from "@angular/forms";

export class User{
    id: Number;
    username: String;
    password: String;
    fullName: String;
    email: String;
    mobile: String;
    gender: String;
    birthDate: Date;
    website: String;
    biography: String;
    role: String;
    isPublic: Boolean;
    receiveMessages: Boolean;
    enableTags: Boolean;
    verified: Boolean;
}