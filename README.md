{
	"info": {
		"_postman_id": "1e843eb0-a841-4161-af38-4c822e4e84dd",
		"name": "Barista-matic",
		"description": "Baristamatic is the backend API for Coffee Ordering App.\n\nThe server maintains an inventory of drink ingredients and is able to prepare a fixed set of possible  \ndrinks by combining these ingredients in different amounts. The cost of a drink is determined by  \nits component ingredients.  \nInitially the Baristamatic should contain 10 units of each ingredient and issuing a restock request  \nshould restore each ingredient to its maximum of 10 units. As drink requests are made,  \ncomponent ingredients should decrease accordingly. Should a drink not be able to be made  \ndue to one or more of the ingredients not having the required quantity, Baristamatic should  \nrespond appropriately. The user can request the current inventory which should list each  \ningredient and the current quantity on-hand.  \nAt any time a menu request should be available that lists the id, name, cost, and availability  \n(based on the ability of Baristamatic to make the drink given its current inventory) for each drink.",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "2038873"
	},
	"item": [
		{
			"name": "inventory",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/inventory",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"inventory"
					]
				},
				"description": "Shows the current inventory which lists each ingredient and current quantity in hand as json."
			},
			"response": []
		},
		{
			"name": "Menu",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/menu",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"menu"
					]
				},
				"description": "Menu API responds with id, name, cost, availability for each drink as JSON payload."
			},
			"response": []
		},
		{
			"name": "Dispense Coffee",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/dispense/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"dispense",
						"1"
					]
				},
				"description": "Responds with \"Dispensed Coffee.\" when all ingredients of drink are in stock.\n\nResponds with error when one or more ingredients to make drink are insufficient or not available.\n\nAvailable quantity of ingredients changed after drink is dispensed."
			},
			"response": []
		},
		{
			"name": "Restock",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/restock",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"restock"
					]
				},
				"description": "Restock request restocks all ingredients to its maximum of 10 units."
			},
			"response": []
		},
		{
			"name": "No Drink found",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8080/dispense/10",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"dispense",
						"10"
					]
				},
				"description": "If any id is used other than 1 to 6, api responds with Drink not found exception"
			},
			"response": []
		},
		{
			"name": "Dispense Decaf Coffee",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/dispense/2",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"dispense",
						"2"
					]
				},
				"description": "Responds with \"Dispensed Decaf Coffee.\" when all ingredients of drink are in stock.\n\nResponds with error when one or more ingredients to make drink are insufficient or not available.\n\nAvailable quantity of ingredients changed after drink is dispensed."
			},
			"response": []
		},
		{
			"name": "Dispense Caffe Latte",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/dispense/3",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"dispense",
						"3"
					]
				},
				"description": "Responds with \"Dispensed Caffe Latte.\" when all ingredients of drink are in stock.\n\nResponds with error when one or more ingredients to make drink are insufficient or not available.\n\nAvailable quantity of ingredients changed after drink is dispensed."
			},
			"response": []
		},
		{
			"name": "Dispense Caffe Americano",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/dispense/4",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"dispense",
						"4"
					]
				},
				"description": "Responds with \"Dispensed Caffe Americano.\" when all ingredients of drink are in stock.\n\nResponds with error when one or more ingredients to make drink are insufficient or not available.\n\nAvailable quantity of ingredients changed after drink is dispensed."
			},
			"response": []
		},
		{
			"name": "Dispense Caffe Mocha",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/dispense/5",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"dispense",
						"5"
					]
				},
				"description": "Responds with \"Dispensed Caffe Mocha.\" when all ingredients of drink are in stock.\n\nResponds with error when one or more ingredients to make drink are insufficient or not available.\n\nAvailable quantity of ingredients changed after drink is dispensed."
			},
			"response": []
		},
		{
			"name": "Dispense Cappuccino",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/dispense/6",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"dispense",
						"6"
					]
				},
				"description": "Responds with \"Dispensed Cappuccino.\" when all ingredients of drink are in stock.\n\nResponds with error when one or more ingredients to make drink are insufficient or not available.\n\nAvailable quantity of ingredients changed after drink is dispensed."
			},
			"response": []
		}
	]
}
