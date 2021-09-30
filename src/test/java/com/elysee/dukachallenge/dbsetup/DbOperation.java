package com.elysee.dukachallenge.dbsetup;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

import java.util.Date;
import java.util.UUID;

public class DbOperation {

    public static Operation INSERT_USER = Operations
            .insertInto("user_tbl")
            .columns("username","password")
            .values("elysee","elysee")
            .build();

    public static Operation INSERT_TODO = Operations
            .insertInto("todo")
            .columns("id","name","description","priority","create_date","modified_date","username")
            .values(UUID.fromString("4fbf908b-085f-41ca-bcf4-fa6303f3cfc9"),"Learning","To learn Math and Computer Science","LOW",new Date(),new Date(),"elysee")
            .build();

    public static Operation DELETE_TODO = Operations
            .deleteAllFrom("todo");

    public static Operation DELETE_USER = Operations
            .deleteAllFrom("user_tbl");
}
