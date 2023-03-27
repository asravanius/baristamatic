Refer to Baristamatic.postman_collection.json uploaded in this project.

Baristamatic is the backend API for Coffee Ordering App.
The server maintains an inventory of drink ingredients and is able to prepare a fixed set of possible
drinks by combining these ingredients in different amounts. The cost of a drink is determined by
its component ingredients.
Initially the Baristamatic should contain 10 units of each ingredient and issuing a restock request
should restore each ingredient to its maximum of 10 units. As drink requests are made,
component ingredients should decrease accordingly. Should a drink not be able to be made
due to one or more of the ingredients not having the required quantity, Baristamatic should
respond appropriately. The user can request the current inventory which should list each
ingredient and the current quantity on-hand.
At any time a menu request should be available that lists the id, name, cost, and availability
(based on the ability of Baristamatic to make the drink given its current inventory) for each drink.

NAME: Inventory API
API: http://localhost:8080/inventory
Sample Response: [
    {
        "name": "Coffee",
        "currentQuantity": 7
    },
    {
        "name": "Decaf Coffee",
        "currentQuantity": 10
    },
    {
        "name": "Sugar",
        "currentQuantity": 9
    },
    {
        "name": "Cream",
        "currentQuantity": 9
    },
    {
        "name": "Steamed Milk",
        "currentQuantity": 8
    },
    {
        "name": "Foamed Milk",
        "currentQuantity": 9
    },
    {
        "name": "Espresso",
        "currentQuantity": 4
    },
    {
        "name": "Cocoa",
        "currentQuantity": 9
    },
    {
        "name": "Whipped Cream",
        "currentQuantity": 9
    }
]

Name: Menu API
API: http://localhost:8080/menu
Sample Response: [
    {
        "id": 1,
        "name": "Coffee",
        "available": true,
        "cost": "$2.75"
    },
    {
        "id": 2,
        "name": "Decaf Coffee",
        "available": true,
        "cost": "$2.75"
    },
    {
        "id": 3,
        "name": "Caffe Latte",
        "available": true,
        "cost": "$2.55"
    },
    {
        "id": 4,
        "name": "Caffe Americano",
        "available": true,
        "cost": "$3.30"
    },
    {
        "id": 5,
        "name": "Caffe Mocha",
        "available": true,
        "cost": "$3.35"
    },
    {
        "id": 6,
        "name": "Cappuccino",
        "available": true,
        "cost": "$2.90"
    }
]

Name: Dispense Coffee API
API: http://localhost:8080/dispense/1
Response: Coffee dispensed.
Note: Replace 1 from {1 to 6} to dispense different drink

Name: Restock API
API: http://localhost:8080/restock
Response: Restock complete.
Note: Restocks each ingredient to its maximum 10 units.







