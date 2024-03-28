import { Binary } from "@angular/compiler";
import { Offer } from "./Offer";

export class Company {
    id: number;
    name: string;
    address: string;
    email: string;
    description: string;
    pnumber: Number;
    offers: Offer[];
    attachmentFileName?: string;
    attachmentData?: Binary;
};