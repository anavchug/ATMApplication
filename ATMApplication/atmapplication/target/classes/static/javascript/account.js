let balance;
// Extract the account number from the URL query parameters
const urlSearchParams = new URLSearchParams(window.location.search);
const loggedInAccountNumber = urlSearchParams.get("accountNumber");

// Fetch the account page for the logged-in account
fetch(`/account?accountNumber=${loggedInAccountNumber}`)
.then(response => response.text())
.then(html => {
    document.body.innerHTML = html;
    balance = parseFloat(document.getElementById("currentBalance").textContent); // Get the initial balance from the page
    updateBalance(); // Initialize the balance display

    // Now that the account page is loaded, get references to the other elements
    const showTransactionHistoryButton = document.getElementById("showTransactionHistory");
    const transactionHistoryContainer = document.getElementById("transactionHistoryContainer");

    // Attach the click event handler after getting the button reference
    showTransactionHistoryButton.addEventListener("click", handleTransactionHistoryButtonClick);
})
.catch(error => console.error('Error fetching account page:', error));


function updateBalance() {
    document.getElementById("currentBalance").textContent = balance;
}
    
function deposit() {
    const depositAmount = parseFloat(document.getElementById("depositAmount").value);
    if (!isNaN(depositAmount) && depositAmount > 0) {
        fetch(`/deposit?accountNumber=${loggedInAccountNumber}&depositAmount=${depositAmount}`, { 
            method: "POST"
        })
        .then(response => response.text())
        .then(message => {
            if (message === "Deposited successfully") {
                balance += depositAmount;
                updateBalance();
                alert(`Deposited $${depositAmount}`);
            } else {
                alert("Account not found");
            }
        })
        .catch(error => console.error('Error depositing funds:', error));
    } else {
        alert("Invalid deposit amount");
    }
}

function withdraw() {
    const withdrawAmount = parseFloat(document.getElementById("withdrawAmount").value);
    if (!isNaN(withdrawAmount) && withdrawAmount > 0) {
        fetch(`/withdraw?accountNumber=${loggedInAccountNumber}&withdrawAmount=${withdrawAmount}`, {
            method: "POST"
        })
        .then(response => response.text())
        .then(message => {
            if (message === "Withdrawn successfully") {
                if (withdrawAmount <= balance) {
                    balance -= withdrawAmount;
                    updateBalance();
                    alert(`Withdrawn $${withdrawAmount}`);
                } else {
                    alert("Insufficient balance");
                }
            } else {
                alert("Account not found");
            }
        })
        .catch(error => console.error('Error withdrawing funds:', error));
    } else {
        alert("Invalid withdrawal amount");
    }
}

updateBalance(); // Initialize the balance display

function handleTransactionHistoryButtonClick() {
    console.log("Button clicked");
    // Toggle the visibility of the transaction history container
    if (transactionHistoryContainer.style.display === "none") {
        transactionHistoryContainer.style.display = "block";
        fetchTransactionHistory(); // Fetch and display the transaction history
        console.log("Fetching transaction history");
    } else {
        transactionHistoryContainer.style.display = "none";
    }
}

function fetchTransactionHistory() {
    // Fetch recent transactions
    fetch(`/transaction-history?accountNumber=${loggedInAccountNumber}`)
        .then(response => response.json())
        .then(transactions => {
            console.log(transactions)
            // Generate the transaction history HTML
            const transactionHistoryHTML = transactions.map(transaction => `
                <p>${transaction.type} of $${transaction.amount} at ${new Date(transaction.localDateTime).toLocaleString()}</p>
            `).join('');
            console.log(transactionHistoryHTML);
            // Insert the transaction history HTML into the container
            transactionHistoryContainer.innerHTML = transactionHistoryHTML;
            console.log(transactionHistoryContainer.innerHTML);
        })
        .catch(error => console.error('Error fetching transaction history:', error));
}