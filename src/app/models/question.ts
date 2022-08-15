import { Option } from "./option";
export class Question {
    constructor(
      public id: number,
      public Type: string,
      public Text: string,
      public options: Option[],
      public Required: boolean,
      public Remarks: string,
      public hasRemarks: boolean
    ) {}
  }