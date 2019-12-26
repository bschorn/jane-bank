

/*
PrimitiveType
    .def("BOOL", Class("org.schorn.ella.node.Primitive.Pbool"))
    .def("INTEGER", Class("org.schorn.ella.node.Primitive.Pinteger"))
    .def("DATE", Class("org.schorn.ella.node.Primitive.Pdate"))
    .def("TIME", Class("org.schorn.ella.node.Primitive.Ptime"))
    .def("TIMESTAMP", Class("org.schorn.ella.node.Primitive.Ptimestamp"))
    .def("DECIMAL", Class("org.schorn.ella.node.Primitive.Pdecimal"))
    .def("TEXT", Class("org.schorn.ella.node.Primitive.Ptext"))
    .def("UUID", Class("org.schorn.ella.node.Primitive.Puuid"))
    .def("DAY_OF_WEEK", Class("org.schorn.ella.node.Primitive.Pdayofweek"));

ConstraintTypes
    .def("pattern", DataGroup.TEXT, Class("java.lang.String))
    .def("list", DataGroup.ENUM, Class("java.lang.String"))
    .def("min_date", DataGroup.DATE, Class("java.lang.LocalDate"))
    .def("max_date", DataGroup.DATE, Class("java.lang.LocalDate"))
    .def("holidays", DataGroup.DATE, Class("java.lang.LocalDate"))
    .def("min_time", DataGroup.TIME, Class("java.lang.LocalTime"))
    .def("max_time", DataGroup.TIME, Class("java.lang.LocalTime"))
    .def("min_datetime", DataGroup.TIMESTAMP, Class("java.lang.LocalDateTime"))
    .def("max_datetime", DataGroup.TIMESTAMP, Class("java.lang.LocalDateTime"))
    .def("min_decimal", DataGroup.DECIMAL, Class("java.lang.BigDecimal"))
    .def("max_decimal", DataGroup.DECIMAL, Class("java.lang.BigDecimal"))
    .def("inc_decimal", DataGroup.DECIMAL, Class("java.lang.BigDecimal"))
    .def("min_integer", DataGroup.INTEGER, Class("java.lang.Integer"))
    .def("max_integer", DataGroup.INTEGER, Class("java.lang.Integer"))
    .def("inc_integer", DataGroup.INTEGER, Class("java.lang.Integer"));


DataTypes
    .def("TEXT", PrimitiveType.TEXT,
        Constraints
            .add(ConstraintType.pattern))
    .def("UUID", PrimitiveType.UUID,
        Constraints
            .add(ConstraintType.pattern))
    .def("DATE", PrimitiveType.DATE,
        Constraints
            .add(ConstraintType.min_date)
            .add(ConstraintType.max_date)
            .add(ConstraintType.holidays))
    .def("TIME", PrimitiveType.TIME,
        Constraints
            .add(ConstraintType.min_time)
            .add(ConstraintType.max_time))
    .def("TIMESTAMP", PrimitiveType.TIMESTAMP,
        Constraints
            .add(ConstraintType.min_datetime)
            .add(ConstraintType.max_datetime))
    .def("DECIMAL", PrimitiveType.DECIMAL,
        Constraints
            .add(ConstraintType.min_decimal)
            .add(ConstraintType.max_decimal)
            .add(ConstraintType.inc_decimal))
    .def("NUMBER", PrimitiveType.INTEGER,
        Constraints
            .add(ConstraintType.min_integer)
            .add(ConstraintType.max_integer)
            .add(ConstraintType.inc_integer))
    .def("BOOL", PrimitiveType.BOOL)
    .def("ENUM", PrimitiveType.TEXT,
        Constraints
            .add(ConstraintType.list))
    .def("LIST", PrimitiveType.TEXT,
        Constraints
            .add(ConstraintType.list));

*/

FieldTypes
    .def("uuid_type", DataType.UUID("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}"))
    .def("bool_type", DataType.BOOL)
    .def("accountCode_type", DataType.TEXT("^A[0-9][0-9][0-9A-Z][0-9A-Z][0-9A-Z][0-9]$"))
    .def("componentCode_type", DataType.TEXT("^C[0-9][0-9][0-9A-Z][0-9A-Z][0-9A-Z][0-9]$"))
    .def("partyCode_type", DataType.TEXT("^P[0-9][0-9][0-9A-Z][0-9A-Z][0-9A-Z][0-9]$"))
    .def("instrumentCode_type", DataType.TEXT("^I[0-9][0-9][0-9A-Z][0-9A-Z][0-9A-Z][0-9]$"))
    .def("transactionCode_type", DataType.TEXT("^T[0-9][0-9][0-9A-Z][0-9A-Z][0-9A-Z][0-9]$"))
    .def("orderCode_type", DataType.TEXT("^O[0-9][0-9][0-9A-Z][0-9A-Z][0-9A-Z][0-9]$"))
    .def("productCode_type", DataType.TEXT("^X[0-9][0-9][0-9A-Z][0-9A-Z][0-9A-Z][0-9]$"))
    .def("journalCode_type", DataType.TEXT("^J[0-9][0-9][0-9A-Z][0-9A-Z][0-9A-Z][0-9]$"))
    .def("ledgerCode_type", DataType.TEXT("^L[0-9][0-9][0-9A-Z][0-9A-Z][0-9A-Z][0-9]$"))
    .def("accountType_type", DataType.TEXT)
    .def("componentType_type", DataType.TEXT)
    .def("partyType_type", DataType.TEXT)
    .def("positionType_type", DataType.TEXT)
    .def("instrumentType_type", DataType.TEXT)
    .def("transactionType_type", DataType.TEXT)
    .def("orderType_type", DataType.TEXT)
    .def("productType_type", DataType.TEXT)
    .def("journalType_type", DataType.TEXT)
    .def("ledgerType_type", DataType.TEXT)
    .def("int_type", DataType.NUMBER)
    .def("ts_type", DataType.TIMESTAMP)
    .def("obj_type", DataType.TEXT)
    .def("name_type", DataType.TEXT("^[a-z ,.'-]{1,60}$"))
    //.def("personName_type", DataType.TEXT("^[A-Za-z\x{00C0}-\x{00FF}][A-Za-z\x{00C0}-\x{00FF}\'\-]+([\ A-Za-z\x{00C0}-\x{00FF}][A-Za-z\x{00C0}-\x{00FF}\'\-]+)*"))
    .def("personName_type", DataType.TEXT("^[a-z ,.'-]{1,60}$"))
    .def("text_type", DataType.TEXT)
    .def("country_type", DataType.ENUM(Read.File("./ref/country.dat")))
    .def("postalCode_type", DataType.TEXT("^\d{5}$"))
    .def("subdivision_type", DataType.TEXT)
    .def("city_type", DataType.TEXT)
    .def("street_type", DataType.TEXT)
    .def("dob_type", DataType.DATE)
    .def("county_type", DataType.TEXT)
    .def("pobox_type", DataType.TEXT)
    .def("prefixName_type", DataType.ENUM(Read.File("./ref/name_prefix.dat")))
    .def("suffixName_type", DataType.ENUM(Read.File("./ref/name_suffix.dat")))
    .def("addressType_type", DataType.TEXT)
    .def("dateOfBirth_type", DataType.DATE)
    .def("gender_type", DataType.ENUM(Read.File("./ref/gender.dat")))
    .def("race_type", DataType.ENUM(Read.File("./ref/race.dat")))
    .def("ethnicity_type", DataType.ENUM(Read.File("./ref/ethnicity.dat")))
    .def("clientRequestType_type", DataType.ENUM(Read.File("./ref/client_request.dat")))
    .def("personTitle_type", DataType.TEXT)
    .def("educationLevel_type", DataType.TEXT)
    .def("passport_type", DataType.TEXT)
    .def("partyRole_type", DataType.TEXT)
;

ValueTypes
    .def("objectId", FieldType.uuid_type, Flags.add(ValueFlag.SYSTEM))
    .def("createTS", FieldType.ts_type, Flags.add(ValueFlag.TEMPORAL).add(ValueFlag.SYSTEM))
    .def("activeTS", FieldType.ts_type, Flags.add(ValueFlag.TEMPORAL).add(ValueFlag.SYSTEM))
    .def("activityTS", FieldType.ts_type, Flags.add(ValueFlag.TEMPORAL).add(ValueFlag.SYSTEM))
    .def("inactiveTS", FieldType.ts_type)
    .def("snapshotTS", FieldType.ts_type)
    .def("eventTS", FieldType.ts_type)
    .def("sequence", FieldType.int_type)
    .def("version", FieldType.int_type)
    .def("partyId", FieldType.uuid_type)
    .def("partyGroupId", FieldType.uuid_type)
    .def("ownerPartyId", FieldType.uuid_type)
    .def("memberPartyId", FieldType.uuid_type)
    .def("accountId", FieldType.uuid_type)
    .def("productId", FieldType.uuid_type)
    .def("componentId", FieldType.uuid_type)
    .def("instrumentId", FieldType.uuid_type)
    .def("orderId", FieldType.uuid_type)
    .def("transactionId", FieldType.uuid_type)
    .def("journalId", FieldType.uuid_type)
    .def("positionId", FieldType.uuid_type)
    .def("ledgerId", FieldType.uuid_type)
    .def("coaId", FieldType.uuid_type)
    .def("partyCd", FieldType.partyCode_type)
    .def("accountCd", FieldType.accountCode_type)
    .def("productCd", FieldType.productCode_type)
    .def("componentCd", FieldType.componentCode_type)
    .def("instrumentCd", FieldType.instrumentCode_type)
    .def("orderCd", FieldType.orderCode_type)
    .def("transactionCd", FieldType.transactionCode_type)
    .def("journalCd", FieldType.journalCode_type)
    .def("ledgerCd", FieldType.ledgerCode_type)
    .def("partyType", FieldType.partyType_type)
    .def("accountType", FieldType.accountType_type)
    .def("addressType", FieldType.addressType_type)
    .def("productType", FieldType.productType_type)
    .def("componentType", FieldType.componentType_type)
    .def("instrumentType", FieldType.instrumentType_type)
    .def("orderType", FieldType.orderType_type)
    .def("transactionType", FieldType.transactionType_type)
    .def("journalType", FieldType.journalType_type)
    .def("positionType", FieldType.positionType_type)
    .def("ledgerType", FieldType.ledgerType_type)
    .def("partyName", FieldType.name_type)
    .def("accountName", FieldType.name_type)
    .def("productName", FieldType.name_type)
    .def("componentName", FieldType.name_type)
    .def("instrumentName", FieldType.name_type)
    .def("orderName", FieldType.name_type)
    .def("transactionName", FieldType.name_type)
    .def("journalName", FieldType.name_type)
    .def("ownerPartyRole", FieldType.partyRole_type)
    .def("memberPartyRole", FieldType.partyRole_type)
    .def("cpAccountType", FieldType.text_type)
    .def("cpAccountId", FieldType.text_type)
    .def("dummy", FieldType.text_type)
    .def("domicile", FieldType.country_type)
    .def("postalCode", FieldType.postalCode_type)
    .def("subdivision", FieldType.subdivision_type)
    .def("city", FieldType.city_type)
    .def("street1", FieldType.street_type)
    .def("street2", FieldType.street_type)
    .def("prefixName", FieldType.personPrefix_type)
    .def("givenName", FieldType.personName_type)
    .def("fullName", FieldType.personName_type)
    .def("surname", FieldType.personName_type)
    .def("suffixName", FieldType.personSuffix_type)
    .def("country", FieldType.country_type)
    .def("county", FieldType.county_type)
    .def("po_box", FieldType.pobox_type)
    .def("prefixName", FieldType.prefixName_type)
    .def("personTitle", FieldType.personTitle_type)
    .def("suffixName", FieldType.suffixName_type)
    .def("dateOfBirth", FieldType.dateOfBirth_type)
    .def("personGender", FieldType.gender_type)
    .def("personRace", FieldType.race_type)
    .def("personEthnicity", FieldType.ethnicity_type)
    .def("passportNumber", FieldType.passport_type)
    .def("personEducation", FieldType.educationLevel_type)
    .def("foreignNational", FieldType.bool_type)
    .def("publicFigure", FieldType.bool_type)
    .def("accountType", FieldType.accountType_type)
    .def("addressType", FieldType.addressType_type)
    .def("componentType", FieldType.componentType_type)
    .def("instrumentType", FieldType.instrumentType_type)
    .def("productType", FieldType.productType_type)
    .def("orderType", FieldType.orderType_type)
    .def("transactionType", FieldType.transactionType_type)
    .def("ledgerType", FieldType.ledgerType_type)
    .def("partyRole", FieldType.partyRole_type)
    .def("clientRequestType", FieldType.clientRequestType_type)
;

Fragments
    .def("Address",
        Members
            .add(ValueType.addressType)
            .add(ValueType.country)
            .add(ValueType.postalCode)
            .add(ValueType.subdivision)
            .add(ValueType.city)
            .add(ValueType.street1)
            .add(ValueType.street2)
            .add(ValueType.county)
            .add(ValueType.po_box))
    .def("Instruction",
        Members
            .add(ValueType.dummy))
    .def("Person",
        Members
            .add(ValueType.prefixName, BondType.OPTIONAL)
            .add(ValueType.givenName, BondType.MUTABLE)
            .add(ValueType.fullName, BondType.MUTABLE)
            .add(ValueType.surname, BondType.MUTABLE)
            .add(ValueType.suffixName, BondType.OPTIONAL)
            .add(ValueType.dateOfBirth, BondType.MUTABLE)
            .add(ValueType.personGender, BondType.MUTABLE)
            .add(ValueType.personRace, BondType.MUTABLE)
            .add(ValueType.personEthnicity, BondType.MUTABLE)
            .add(ValueType.personTitle, BondType.MUTABLE)
            .add(ValueType.personEducation, BondType.MUTABLE)
            .add(ValueType.passportNumber, BondType.MUTABLE)
            .add(ValueType.foreignNational, BondType.MUTABLE)
            .add(ValueType.publicFigure, BondType.MUTABLE))
    .def("Organization",
        Members
            .add(ValueType.dummy));

BaseTypes
    .def("Object", 
        Members
            .add(ValueType.createTS))
    .def("Entity", 
        Parents
            .add(BaseType.Object),
        Members
            .add(ValueType.activeTS)
            .add(ValueType.version))
    .def("Associative",
        Parents
            .add(BaseType.Entity),
        Members
            .add(ValueType.inactiveTS))
    .def("Activity",
        Parents
            .add(BaseType.Object))
    .def("Process",
        Parents
            .add(BaseType.Activity),
        Members
            .add(ValueType.activityTS)
            .add(ValueType.sequence))
    .def("Event",
        Parents
            .add(BaseType.Activity),
        Members
            .add(ValueType.eventTS))
    .def("Aggregate", 
        Parents
            .add(BaseType.Object),
        Members
            .add(ValueType.snapshotTS));


ObjectTypes
    .def("Party",
        Attributes
            .add(ObjectCategory.FACT)
            .add(ObjectPurpose.ENTITY)
            .add(ObjectLevel.ENTERPRISE),
        Parents
            .add(BaseType.Entity),
        Members
            .add(ValueType.partyId, BondType.PRIMARY_KEY)
            .add(ValueType.partyCd, BondType.NATURAL_KEY)
            .add(ValueType.partyType)
            .add(ValueType.partyName)
            .add(ValueType.domicile)
            .add(Fragment.Address))
    .def("Account", 
        Attributes
            .add(ObjectCategory.FACT)
            .add(ObjectPurpose.ENTITY)
            .add(ObjectLevel.LOB),
        Parents
            .add(BaseType.Entity),
        Members
            .add(ValueType.accountId, BondType.PRIMARY_KEY)
            .add(ValueType.accountCd, BondType.NATURAL_KEY)
            .add(ValueType.accountType)
            .add(ValueType.accountName)
            .add(ValueType.partyId, BondType.FOREIGN_KEY)
            .add(ValueType.productId, BondType.FOREIGN_KEY))
    .def("Instrument", 
        Attributes
            .add(ObjectCategory.FACT)
            .add(ObjectPurpose.ENTITY)
            .add(ObjectLevel.UNIVERSAL),
        Parents
            .add(BaseType.Entity),
        Members
            .add(ValueType.instrumentId, BondType.PRIMARY_KEY)
            .add(ValueType.instrumentCd, BondType.NATURAL_KEY)
            .add(ValueType.instrumentType)
            .add(ValueType.instrumentName))
    .def("Product", 
        Attributes
            .add(ObjectCategory.FACT)
            .add(ObjectPurpose.ENTITY)
            .add(ObjectLevel.LOB),
        Parents
            .add(BaseType.Entity),
        Members
            .add(ValueType.productId, BondType.PRIMARY_KEY)
            .add(ValueType.productCd, BondType.NATURAL_KEY)
            .add(ValueType.productType)
            .add(ValueType.productName))
    .def("Component", 
        Attributes
            .add(ObjectCategory.FACT)
            .add(ObjectPurpose.ENTITY)
            .add(ObjectLevel.LOB),
        Parents
            .add(BaseType.Entity),
        Members
            .add(ValueType.componentId, BondType.PRIMARY_KEY)
            .add(ValueType.componentCd, BondType.NATURAL_KEY)
            .add(ValueType.componentName))
    .def("Order", 
        Attributes
            .add(ObjectCategory.ACT)
            .add(ObjectPurpose.REQUEST)
            .add(ObjectLevel.LOB),
        Parents
            .add(BaseType.Activity),
        Members
            .add(ValueType.orderId, BondType.PRIMARY_KEY)
            .add(ValueType.orderCd, BondType.NATURAL_KEY)
            .add(ValueType.orderType)
            .add(ValueType.accountId, BondType.FOREIGN_KEY)
            .add(ValueType.instrumentId, BondType.FOREIGN_KEY)
            .add(ValueType.cpAccountType)
            .add(ValueType.cpAccountId)
            .add(Fragment.Instruction))
    .def("Transaction", 
        Attributes
            .add(ObjectCategory.ACT)
            .add(ObjectPurpose.EXECUTION)
            .add(ObjectLevel.LOB),
        Parents
            .add(BaseType.Activity),
        Members
            .add(ValueType.transactionId, BondType.PRIMARY_KEY)
            .add(ValueType.transactionCd, BondType.NATURAL_KEY)
            .add(ValueType.transactionType)
            .add(ValueType.orderId, BondType.FOREIGN_KEY))
    .def("Journal", 
        Attributes
            .add(ObjectCategory.ACT)
            .add(ObjectPurpose.EXECUTION)
            .add(ObjectLevel.LOB),
        Parents
            .add(BaseType.Activity),
        Members
            .add(ValueType.journalId, BondType.PRIMARY_KEY)
            .add(ValueType.journalCd, BondType.NATURAL_KEY)
            .add(ValueType.journalType)
            .add(ValueType.transactionId, BondType.FOREIGN_KEY))
    .def("Position", 
        Parents
            .add(BaseType.Aggregate),
        Members
            .add(ValueType.positionId, BondType.PRIMARY_KEY)
            .add(ValueType.positionType))
    .def("Ledger", 
        Parents
            .add(BaseType.Aggregate),
        Members
            .add(ValueType.ledgerId, BondType.PRIMARY_KEY)
            .add(ValueType.ledgerCd, BondType.NATURAL_KEY)
            .add(ValueType.ledgerType))
            //.add(ValueType.coaId, BondType.FOREIGN_KEY))
    .def("PartyAddress",
        Parents
            .add(BaseType.Entity),
        Members
            .add(Fragment.Address))
    .def("PartyPerson",
        Parents
            .add(ObjectType.Party),
        Members
            .add(Fragment.Person))
    .def("PartyOrganization",
        Parents
            .add(ObjectType.Party),
        Members
            .add(Fragment.Organization))
    .def("PartyGroup",
        Parents
            .add(ObjectType.Party),
        Members
            .add(ValueType.ownerPartyId, BondType.FOREIGN_KEY(Party.partyId))
            .add(ValueType.partyRole))
    .def("PartyGroupMembers", 
        Parents
            .add(BaseType.Associative),
        Members
            .add(ValueType.partyGroupId, BondType.FOREIGN_KEY(PartyGroup.partyId))
            .add(ValueType.partyId, BondType.FOREIGN_KEY(Party.partyId))
            .add(ValueType.partyRole))
    .def("CustomerAccount",
        Parents
            .add(ObjectType.Account))
    .def("DepositAccount",
        Parents
            .add(ObjectType.CustomerAccount))
    .def("SavingsAccount",
        Parents
            .add(ObjectType.CustomerAccount))
    .def("HouseAccount",
        Parents
            .add(ObjectType.Account))
    .def("PrincipalAccount",
        Parents
            .add(ObjectType.HouseAccount))
    .def("DepositProduct",
        Parents
            .add(ObjectType.Product))
    .def("SavingsProduct",
        Parents
            .add(ObjectType.Product))
    .def("LoyaltyProduct",
        Parents
            .add(ObjectType.Product))
    .def("TimeDepositProduct",
        Parents
            .add(ObjectType.Product))
    .def("LineOfCreditProduct",
        Parents
            .add(ObjectType.Product))
    .def("TimeDepositComponent",
        Parents
            .add(ObjectType.Component))
    .def("FeeComponent",
        Parents
            .add(ObjectType.Component))
    .def("LimitsComponent",
        Parents
            .add(ObjectType.Component))
    .def("TransferOrder",
        Parents
            .add(ObjectType.Order))
    .def("PaymentOrder",
        Parents
            .add(ObjectType.Order))
    .def("CollectionOrder",
        Parents
            .add(ObjectType.Order))
    .def("StandingOrder",
        Parents
            .add(ObjectType.Order))
    .def("StandingPayment",
        Parents
            .add(ObjectType.StandingOrder)
            .add(ObjectType.PaymentOrder))
    .def("StandingCollection",
        Parents
            .add(ObjectType.StandingOrder)
            .add(ObjectType.CollectionOrder))
    .def("TimeDepositPosition",
        Parents
            .add(ObjectType.Position))
    .def("FeePosition",
        Parents
            .add(ObjectType.Position))
    .def("LimitsPosition",
        Parents
            .add(ObjectType.Position))
    .def("AvailableRequest",
        Members
            .add(ValueType.clientRequestType, BondType.PRIMARY_KEY))
;


/*

Reductions
    .def("Trade",
        Inputs
            .def("NetAmt", DataType.DECIMAL)
            .def("Qty", DataType.DECIMAL)
            .def("Px", DataType.DECIMAL)),
        Outputs
            .def("TotNetAmt", DataType.DECIMAL)
            .def("TotQty", DataType.DECIMAL)
            .def("TotAmt", DataType.DECIMAL)
            .def("AvgPx", DataType.DECIMAL)
            .def("AvgNetPx", DataType.DECIMAL)),
        Rules
            .add(Output.TotNetAmt, Math.sum(Input.NetAmt))
            .add(Output.TotQty, Math.sum(Input.Qty))
            .add(Output.TotAmt, Math.multiply(Input.Qty, Input.Px))
            .add(Output.AvgPx, Math.divide(Math.sum(Math.multiply(Input.Qty, Input.Px)), Math.sum(Input.Qty)))
            .add(Output.AvtNetPx, Math.divide(Math.sum(Input.NetAmt), Math.sum(Input.Qty))));

Groups
    .def("ByAccount",
        Members
            .add(ValueType.tradeDate)
            .add(ValueType.accountId)
            .add(ValueType.productId)
            .add(ValueType.longShort))
    .def("ByStrategy",
        Members
            .add(ValueType.tradeDate)
            .add(ValueType.strategyId)
            .add(ValueType.productId)
            .add(ValueType.longShort))
    .def("ByBroker",
        Members
            .add(ValueType.tradeDate)
            .add(ValueType.brokerId)
            .add(ValueType.productId)
            .add(ValueType.longShort))
    .def("ByBrokerAccount",
        Members
            .add(ValueType.tradeDate)
            .add(ValueType.brokerId)
            .add(ValueType.accountId)
            .add(ValueType.productId)
            .add(ValueType.longShort))


Reports
    .def("TradeSummaryByAccount", ObjectType.TradeSummary,
        From(ObjectType.Trade),
        SelectType.TradeSummary,
        GroupBy.ByAccount)
    .def("TradeSummaryByStrategy", ObjectType.TradeSummary,
        SelectType.TradeSummary,
        From.Trade,
        GroupBy.ByStrategy)
    .def("TradeSummaryByBroker", ObjectType.TradeSummary,
        SelectType.TradeSummary,
        FromType.Trade,
        GroupBy.ByBroker)
    .def("TradeSummaryByBrokerAccount", ObjectType.TradeSummary,
        SelectType.TradeSummary,
        FromType.Trade,
        GroupBy.ByBrokerAccount)



*/