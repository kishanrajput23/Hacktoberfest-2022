const operatorList = document.querySelectorAll(".operator");
const operandList = document.querySelectorAll(".operand");
const result = document.querySelector("#input-screen");
let displayString = "";
const operatorSymbols = ["/", "+", "-", "*", "."];
const operandSymbols = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."];

result.value = displayString;

const populateResult = () => (result.value = displayString);

const clearResult = () => (displayString = "");

const getLastChar = (string) => string.charAt(string.length - 1);

const deleteLastCharacter = () =>
  (displayString = displayString.substring(0, displayString.length - 1));

const checkIfLastCharIsOperator = (string) =>
  operatorSymbols.includes(getLastChar(string));

const handleOperation = (operation) => {
  if (checkIfLastCharIsOperator(displayString)) {
    deleteLastCharacter();
  }
  displayString += operation;

  populateResult();
};

const computeResult = () => `${eval(displayString)}`;

const handleOperandClick = (e) => {
  const operand = e.target.value;
  if (operandSymbols.includes(operand)) {
    displayString +=
      operand !== "."
        ? operand
        : checkIfLastCharIsOperator(displayString)
        ? ""
        : operand;
  }
  populateResult();
};

const handleOperatorClick = (e) => {
  const operation = e.target.value;

  switch (operation) {
    case "AC":
      clearResult();
      populateResult();
      break;
    case "DEL":
      deleteLastCharacter();
      populateResult();
      break;
    case "+":
    case "*":
    case "-":
    case "/":
      handleOperation(e.target.value);
      break;
    case "=":
      displayString = computeResult();
      populateResult();
      break;
    default:
      break;
  }
};

const init = () => {
  operandList.forEach((operand) => {
    operand.addEventListener("click", handleOperandClick);
  });

  operatorList.forEach((operator) => {
    operator.addEventListener("click", handleOperatorClick);
  });
};

window.addEventListener("load", init);
