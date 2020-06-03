# Backend

## Domains

### Truck
The domain Truck have the necessary attributes about the an Truck.
Including a few more attributes from different domains, such as **Segment** and **Color**
This two relationships are **ManyToMany**, because a truck has many segments and colors.
Also, to reuse those domains in case of the creation of another truck with one of those segments
or colors.

### Segment
The Segment domain exists with the objective to save the possible segments
of a truck.

### Color
The Color domain in the same way of **Segment** exists with the objective to save the possible 
colors of a truck.

## Repository

### TruckRepository
The TruckRepository it's the layer to handle JPA methods in Spring Context, with the object to
persist data in the database.
This repository also extends the JPA Specification, that is used for advance queries with Spring Data. 

### SegmentsRepository
The SegmentRepository was created to persist data about the segment domain and also
to query using the Spring Data with keywords to filter data with contains and also ignore Upper/Lower cases.

### ColorRepository
The ColorRepository have the same objective and behavior as **SegmentsRepository**.

## Services

### TruckService
The only domain that contains a Service, it's Truck, the reason of that it's was necessary handle the relations
with segments and color, in case of creating a new truck with new segments/colors or a new truck
with already persisted segments/colors.

## Controllers

### TruckController
The TruckController have all mandatory http methods to create,find,list,delete a truck.
Also was added to the controller the functionality to find trucks with filter and pagination.

### SegmentController
The SegmentController was created with the object to find a segment by description, using to
filter the segments based on a string.
This functionality is handle by the **frontend** querying a description with more than 2 characters.

### ColorController
The ColorController have the same objective and behavior as **SegmentController**.


# FrontEnd

## Models
The models Color, Segment and Truck, are the typescript representation of the domains of the backend.

## Modules

### Truck
The modules folder it's used to structure the frontend app based on domains.
The truck module, have all necessary components to handle the truck data, like create, list, delete and edit.
The components was devided by functionalities, see the list below.

- truck-create: Inside this component, you will find another components to handle the truck form. Also, components to search a segment/color to add in a truck or to add a new segments/color to also persist with the truck.
- truck-delete-modal: This component is simple modal to confirm with user if the user really want to delete a record.
- truck-list: Inside this component, you will find the truck-grid, that is used to show the table with all the trucks. Also, inside this folder you will find the truck-search with the truck-search-form inside to handle the filters for search a truck.
- truck-modal-edit: This component is used to render the truck-form to edit any truck information.

**The components truck-create and truck-modal-edit, reuse the same truck-create-form to render the data before save/update.**

**The Truck Module is configured to be loaded in the lazy way**

### Shared
The shared module was created with the objective to store all shared common components 
such as alerts, modals, toast and etc.
In this case, a alert component was created to show messages when triggered.


## Services
The services files were created to handle the api requests for each backend resource.
A BaseService were created to add few common configuration like the headers.