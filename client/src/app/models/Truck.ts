import { Segment } from './Segment';
import { Color } from './Color';

export class Truck {

    id: number;
    model: string;
    enginePower: string;
    fuel: string;
    range: string;
    segments: Segment[];
    colors: Color[];
}
