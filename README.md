### Delivery Problem 

#### problem # 1
<br>
Calculate the discount and net cost of each package for kiki's delivery business.

<strong>DeliveryCostEstimation.java</strong> - This class is used to calculate the discount, net cost for each package
when base delivery cost, package weight, package distance, offer coupon id and package id are given as input.

<strong>OfferCoupon.java</strong> is the class used to validate the offer coupon id supplied in the package details. 
This class gets the offer details from CostEstimationService.java and validates if the package qualifies for the 
discount.

<strong>offer-coupons.csv</strong> file in resources folder contains the offer coupon details. This is read in 
CostEstimationService.java during in constructor and the details are passed to OfferCoupon.java for validation.
Offers can be added or updated without build and deployment when externalized. This is added after the initial review.

Note : Always add both minimum and maximum values for weight and distance. If the minimum value is absent, add 0
(Refer OFR001)

Note : If the Offer Coupon Id provided during input is not present in the factory, then <strong>InvalidCouponIdException
</strong> is thrown and handled to set discount value to 0.

<strong>EstimateCostCommand.java</strong> is the command which is initialized following ***Singleton Design Pattern***.
This command is called by the DeliveryCostEstimation.java to print the results of the discount and net cost.
Above Command Class then in-turn calls the <strong>CostEstimationService.java</strong> which is again initialized by 
***Singleton Design Pattern*** calculates the details and sends it to command class which then print results to cli.

Note : ***IncompletePackageDetailsException*** is thrown when any incomplete input for package details are entered. 

##### Cost Estimation Formula:

Total cost = Base Delivery Cost + (Package Total Weight * 10) + (Distance to Destination * 5). <br>
Discount calculation is based on Offer coupon id supplied during input.<br>
Net Cost = Total Cost - Discount</br>

##### Run Instructions

- Make sure that you had installed maven in your machine. Maven version must be above 3.3.1 for the exec plugin to
support multiple main classes.
- clone the project from the repo url https://github.com/believe-Mahesh/Delivery.git
- Run cd Delivery in cli to change directory into the project folder.
- Run mvn clean install
- Run mvn exec:java@cost-estimation
- This will prompt for the input values.


##### Input and Output from CLI

```
Enter the base delivery cost and number of packages in the following format.
base delivery cost
number of packages
100
3
the values entered are 100.0 and package count is 3
Enter the details of package 1 in following format.
PackageId WeightInKG DistanceInKM OfferCouponId
PKG1 5 5 OFR001
Enter the details of package 2 in following format.
PackageId WeightInKG DistanceInKM OfferCouponId
PKG2 15 5 OFR002
Enter the details of package 3 in following format.
PackageId WeightInKG DistanceInKM OfferCouponId
PKG3 10 100 OFR003
Offer Not applicable for the given package details 
packageId:PKG1 weight:5.0 distance:5.0 couponId:OFR001 timeInHrs: null
Offer Not applicable for the given package details 
packageId:PKG2 weight:15.0 distance:5.0 couponId:OFR002 timeInHrs: null
PKG1 0.0 175.0
PKG2 0.0 275.0
PKG3 35.0 665.0
***Bye***
```

<hr>

#### problem # 2

Calculate the estimated delivery time for each package. The delivery time must also include the waiting time before
delivery.

Input - base delivery cost, number of packages, package details, number of vehicles for shipment, max speed the vehicle
can travel, max weight each vehicle can carry.

Assumptions:
- each vehicle must carry as much weight as possible within max limit.
- must prefer heavier packages first, if the weights are same, then prefer short distance packages.
- All vehicles will travel at max speed
- All Vehicles will travel to and fro in same route.

##### Formula to calculate time 
time in hrs = Distance in km / Speed in (km/hr)

##### Approach
- sort the input package list by weight in descending order or if the weight is same then sort by 
distance in ascending. - ***PackageDetailsComparator***
- After sorting, iterate the packages list to create a shipment list by grouping packages by weight.
- Iterate the above shipment list and calculate the speed based on number of vehicles. The vehicle has to deliver the
package and come back, so the waiting time must be 2*delivery time.
- So when the next vehicle frees up, the time for delivery will be waiting time + delivery time.

***EstimateTimeCommand.java*** is the command class used to calculate the estimated delivery time for each package.

***ShipmentService.java*** contains the implementation for time estimation.


##### Run Instructions

- Make sure that you had installed maven in your machine. Maven version must be above 3.3.1 for the exec plugin to
  support multiple main classes.
- clone the project from the repo url https://github.com/believe-Mahesh/Delivery.git
- Run cd Delivery in cli to change directory into the project folder.
- Run mvn clean install
- Run mvn exec:java@time-estimation
- This will prompt for the input values.

##### Input and Output from cli

```Enter the base delivery cost and number of packages in the following format.
base delivery cost
number of packages
100
5
the values entered are 100.0 and package count is 5
Enter the details of package 1 in following format.
PackageId WeightInKG DistanceInKM OfferCouponId
PKG1 50 30 OFR001
Enter the details of package 2 in following format.
PackageId WeightInKG DistanceInKM OfferCouponId
PKG2 75 125 OFR008
Enter the details of package 3 in following format.
PackageId WeightInKG DistanceInKM OfferCouponId
PKG3 175 100 OFR003
Enter the details of package 4 in following format.
PackageId WeightInKG DistanceInKM OfferCouponId
PKG4 110 60 OFR002
Enter the details of package 5 in following format.
PackageId WeightInKG DistanceInKM OfferCouponId
PKG5 155 95 NA
Enter the details of number of vehicles, max_speed, max weight in the following format
number_of_vehicles max_speed max_weight
2 70 200
the value of shipment details : vehicleCount :2 maxSpeed:70.0 maxWeight:200.0
the size of entered list is 5
Offer Not applicable for the given package details 
packageId:PKG1 weight:50.0 distance:30.0 couponId:OFR001 timeInHrs: null
OFR008 is not a valid coupon id
Offer Not applicable for the given package details 
packageId:PKG3 weight:175.0 distance:100.0 couponId:OFR003 timeInHrs: null
NA is not a valid coupon id
PKG1 0.0 750.0 3.98
PKG2 0.0 1475.0 1.78
PKG3 0.0 2350.0 1.42
PKG4 105.0 1395.0 0.85
PKG5 0.0 2125.0 4.1899999999999995
***Bye***```
