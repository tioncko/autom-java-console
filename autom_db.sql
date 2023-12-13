-- CADASTRO

CREATE TABLE Employee
(
    EmployeeId serial NOT NULL,
    Name VARCHAR NULL,
    Age INT NULL,
    Gender VARCHAR NULL,
    Email VARCHAR NULL,
    PhoneNumber VARCHAR NULL,
    ZipCode VARCHAR NULL,
    Address VARCHAR NULL,
    Number INT NULL,
    Neighborhood VARCHAR NULL,
    City VARCHAR NULL,
    FederatedState VARCHAR NULL,
    Area VARCHAR NULL,
    Department VARCHAR NULL,
    InsertUser VARCHAR(20) -- Coluna do banco
        default TO_CHAR(current_timestamp, 'yyyy-MM-dd HH:mm:ss'),

    constraint PK_EmployeeId PRIMARY KEY(EmployeeId)
);

CREATE TABLE UserPermission
(
    UserPermissionId serial NOT NULL,
    Permission VARCHAR NULL,
    
    constraint PK_IdUserPermission PRIMARY KEY(UserPermissionId)
);

CREATE TABLE Users
(
    UserId serial NOT NULL,
    Login VARCHAR NULL,
    Password CHAR (40) NULL,
    UserName VARCHAR NULL,
    EmployeeId INT NULL, -- implementar na classe e retirar a variavel departamento da classe atual
    UserPermissionId INT NULL,
    UpdateUser VARCHAR NULL, -- Coluna do banco
    InsertUser VARCHAR(20) -- Coluna do banco
        default TO_CHAR(current_timestamp, 'yyyy-MM-dd HH:mm:ss'),

    CONSTRAINT PK_UserId PRIMARY KEY (UserId),
    CONSTRAINT FK_EmployeeId FOREIGN KEY (EmployeeId)
        REFERENCES Employee (EmployeeId) ON DELETE SET NULL,
    CONSTRAINT FK_UserPermissionId FOREIGN KEY (UserPermissionId)
        REFERENCES UserPermission (UserPermissionId) ON DELETE SET NULL
);

CREATE TABLE Customer
(
    CustomerId serial NOT NULL,
    Name VARCHAR NULL,
    Age INT NULL,
    SSC VARCHAR NULL, -- Social Security Code
    Email VARCHAR NULL,
    PhoneNumber VARCHAR NULL,
    ZipCode VARCHAR NULL,
    Address VARCHAR NULL,
    Number INT NULL,
    Neighborhood VARCHAR NULL,
    City VARCHAR NULL,
    FederatedState VARCHAR NULL,
    InsertUser VARCHAR(20) -- Coluna do banco
        default TO_CHAR(current_timestamp, 'yyyy-MM-dd HH:mm:ss'),

    CONSTRAINT PK_CustomerId PRIMARY KEY (CustomerId)
);

CREATE TABLE Supplier
(
    SupplierId serial NOT NULL,
    CorporateName VARCHAR NULL,
    TradeName VARCHAR NULL,
    EIN VARCHAR NULL, -- Employer Identification Number
    Email VARCHAR NULL,
    StateRegistration VARCHAR NULL,
    TownshipRegistration VARCHAR NULL,
    PhoneNumber VARCHAR NULL,
    ZipCode VARCHAR NULL,
    Address VARCHAR NULL,
    Number INT NULL,
    Neighborhood VARCHAR NULL,
    City VARCHAR NULL,
    FederatedState VARCHAR NULL,
    InsertUser VARCHAR(20) -- Coluna do banco
        default TO_CHAR(current_timestamp, 'yyyy-MM-dd HH:mm:ss'),

    CONSTRAINT PK_SupplierId PRIMARY KEY (SupplierId)
);

CREATE TABLE CarBrand
(
    CarBrandId serial NOT NULL,
    Brand VARCHAR NULL,

    CONSTRAINT PK_CarBrandId PRIMARY KEY (CarBrandId)
);

CREATE TABLE Cars
(
    CarId serial NOT NULL,
    CarName VARCHAR NULL,
    Plate VARCHAR NULL,
    Origin VARCHAR NULL,
    CustomerId INT NULL,
    CarBrandId INT NULL,

    CONSTRAINT PK_CarId PRIMARY KEY (CarId),
    CONSTRAINT FK_CarBrandId FOREIGN KEY (CarBrandId)
        REFERENCES CarBrand (CarBrandId) ON DELETE SET NULL,
    CONSTRAINT FK_CustomerId FOREIGN KEY (CustomerId)
        REFERENCES Customer (CustomerId) ON DELETE SET NULL
);

CREATE TABLE Category
(
    CategoryId serial NOT NULL,
    Description VARCHAR NULL,

    CONSTRAINT PK_CategoryId PRIMARY KEY (CategoryId)
);

CREATE TABLE Bunch  -- Criar Classe no ambiente
(
    BunchId serial NOT NULL,
    Description VARCHAR NULL,

    CONSTRAINT PK_BunchId PRIMARY KEY (BunchId)
);

CREATE TABLE Products
(
    ProductId serial NOT NULL,
    Description VARCHAR NULL,
    Price VARCHAR NULL,
    Amount INT NULL,
    SupplierId INT NULL,
    CategoryId INT NULL,
    BunchId INT NULL,
    InsertUser VARCHAR(20) -- Coluna do banco
        default TO_CHAR(current_timestamp, 'yyyy-MM-dd HH:mm:ss'),

    CONSTRAINT PK_ProductId PRIMARY KEY (ProductId),
    CONSTRAINT FK_SupplierId FOREIGN KEY (SupplierId)
        REFERENCES Supplier (SupplierId) ON DELETE SET NULL,
    CONSTRAINT FK_CategoryId FOREIGN KEY (CategoryId)
        REFERENCES Category (CategoryId) ON DELETE SET NULL,
    CONSTRAINT FK_BunchId FOREIGN KEY (BunchId)
        REFERENCES Bunch (BunchId) ON DELETE SET NULL
);

CREATE TABLE Services
(
    ServiceId serial NOT NULL,
    Description VARCHAR NULL,
    Price VARCHAR NULL,
    EmployeeId INT NULL,
    CategoryId INT NULL,
    BunchId INT NULL,
    InsertUser VARCHAR(20) -- Coluna do banco
        default TO_CHAR(current_timestamp, 'yyyy-MM-dd HH:mm:ss'),

    CONSTRAINT PK_ServiceId PRIMARY KEY (ServiceId),
    CONSTRAINT FK_EmployeeId FOREIGN KEY (EmployeeId)
        REFERENCES Employee (EmployeeId) ON DELETE SET NULL,
    CONSTRAINT FK_CategoryId FOREIGN KEY (CategoryId)
        REFERENCES Category (CategoryId) ON DELETE SET NULL,
    CONSTRAINT FK_BunchId FOREIGN KEY (BunchId)
        REFERENCES Bunch (BunchId) ON DELETE SET NULL
);

-- LOJA

