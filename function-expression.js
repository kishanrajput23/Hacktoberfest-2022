var y = 1;

console.log(y);
if (function f(){}) {
    y += typeof f;
    console.log(y);
}
console.log(y);