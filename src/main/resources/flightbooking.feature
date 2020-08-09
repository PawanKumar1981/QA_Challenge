Feature: flight booking portal Testing		

Scenario Outline:: Search for a given flight source and destination and get the fares

Given user navigate flight protal like spicejet.com			

When Enter the source "<source>" and destination "<destination>"			

Then Select the best itinerary based on the fastest and cheapest travel					


Examples:
|source         |destination|
|Delhi (DEL)     |Hyderabad (HYD) |

