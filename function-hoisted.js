let obj=['love', 'blue', 1016, 'india']
var car =  Vehicle.apply({}, obj);
console.log(car);

function Vehicle(model, color, year, country) {
    this.model = model;
    this.color = color;
    this.year = year;
    this.country = country;
    return this
}
