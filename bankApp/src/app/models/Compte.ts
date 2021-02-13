import {Customer} from "./Customer";
import {Operation} from "./Operation";

export class Compte {
  id?: number
  solde?:number
  dateCreation?:string
  type?:string
  etat?:string
  customerID?:number
  customer?:Customer
  operations?:Array<Operation>
}
