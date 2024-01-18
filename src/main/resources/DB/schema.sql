DO $$ 
BEGIN 
    RAISE NOTICE 'Executing init.sql - My Table Creation'; 
END $$;
/*使用者資料*/
CREATE TABLE Users (
  Id SERIAL PRIMARY KEY,
  PhoneNumber INT NOT NULL,
  Password VARCHAR(255) NOT NULL,
  UserName VARCHAR(255) NOT NULL,
  RegistrationTime TIMESTAMP NOT NULL
);

/*書籍資訊*/
CREATE TABLE Book (
  ISBN VARCHAR(13) PRIMARY KEY,
  Name VARCHAR(255) NOT NULL,
  Author VARCHAR(255),
  Introduction VARCHAR(255)
);

/*庫存資料表(關連到書籍資料)*/
CREATE TABLE Inventory (
  InventoryID SERIAL PRIMARY KEY,
  ISBN VARCHAR(13),
  StoreTime TIMESTAMP NOT NULL,
  Status VARCHAR(255) NOT NULL,
  FOREIGN KEY(ISBN) REFERENCES Book(ISBN)
);

/*借閱紀錄*/
CREATE TABLE BorrowingRecord (
  UserID SERIAL,
  InventoryID SERIAL,
  BorrowingTime TIMESTAMP NOT NULL,
  ReturnTime TIMESTAMP,
  FOREIGN KEY (UserID) REFERENCES Users(Id),
  FOREIGN KEY (InventoryID) REFERENCES Inventory(InventoryID)
);
