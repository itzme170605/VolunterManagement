import { Need } from "./need";
import { UserService } from "./user.service";

export class User {
  userName!: string;
  password!: string;
  type!: string; // Can be 'helper' or 'admin'
  manager!:boolean;
}

export class Helper extends User {
  email!: string;
  phoneNumber!: string;
  location!: string;
  basket!: Need[]; // Array of Needs
  schedule!: { need: Need; dateTime: Date }[];
  donated!: number;

  constructor(basket?: Need[], schedule?:{ need: Need; dateTime: Date }[]) {
    super();
    // Only initialize basket if it is not provided or is empty
    this.basket = basket && basket.length > 0 ? basket : [];
    this.schedule = schedule && schedule.length > 0 ? schedule : [];
  }

  // Add a need to the basket
  addNeed(need: Need) {
    this.basket.push(need);
  }

  // Remove a need from the basket
  removeNeed(need: Need) {
    const index = this.basket.indexOf(need);
    if (index !== -1) {
      this.basket.splice(index, 1);
    }
  }

  // Checkout the basket
  checkout() {
    console.log('Checkout: ', this.basket);
    // Clear basket after checkout
    this.basket = [];
  }
}

export class Manager extends User {
  constructor() {
    super();
    this.type = 'admin';  // Manager has type 'admin'
  }
}
