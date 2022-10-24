stocks = [7, 1, 5, 3, 6, 4]
buy = 0;
sell = 0;
n = stocks.length
i = 0
while (i < n) {
    if (Math.max(stocks[i]) == stocks[0]) {
        stocks.splice(0, 1)
    }
    break;
}
for (i = 0; i < n; i++) {
    if (true) {
        console.log(Math.max(stocks[i]))
    }
}
// if (stocks[o] == Ma)










// var n = prices.length;


// buy = 0;
// sell = 0;
// for (let i = 0; i < n; i++) {
//     if (prices[0] == Math.max(prices[i])) {
//         prices.splice(0, 1);
//     }
//     console.log(prices)

//     if (Math.min(prices[i]) == true) {
//         buy = Math.max(prices[i])
//     }
//     if (Math.max(prices[i]) < ) {
//         sell = Math.max(prices[i])
//     }
// }

// console.log(buy)
// console.log(sell)

// // }