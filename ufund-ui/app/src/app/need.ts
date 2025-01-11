import { Helper, User } from "./user.model";

export interface Need {
    id: number;
    name: string;
    facility: string;
    description: string;
    datetime: string;
    active: boolean;
    required: number;
    volunteerCount: number;
    volunteers: string[];
}