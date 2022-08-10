import { Question } from "./question";
export class Survey {
    constructor(
      public id: number,
      public Description: string,
      public Url: string,
      public Title: string,
      public IsDeleted: boolean,
      public IsAvailable: boolean,
      public Question: Question[],
      public Date: Date,
      public Files: File[]
    ) {}
  }