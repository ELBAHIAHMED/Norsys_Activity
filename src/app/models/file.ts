export class File {
    constructor(
        public lastModified:number,
        public lastModifiedDate:Date,
        public name:string,
        public size:number,
        public type:string
    )
    {}
}
