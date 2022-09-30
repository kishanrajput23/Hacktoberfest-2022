function foo() {
    let y = x = 0; // window object 
    x++;
    y++;
    return x;
}

console.log(foo(), typeof x, typeof y);
