package appzspot.sqlitetester.model.dto;

import com.appzspot.easysql.annotations.EzColumn;
import com.appzspot.easysql.annotations.EzPk;
import com.appzspot.easysql.annotations.EzTable;

/**
 * Created by Muhammad Faisal Nadeem on 10/25/2017.
 * Copyright © 2017 by Muhammad Faisal Nadeem
 * <p>
 * All information contained herein is, and remains
 * the property of Muhammad Faisal Nadeem. No part of this document
 * may be reproduced, distributed, or transmitted in any form or by any means
 * without the prior written permission of the publisher.
 * <p>
 * For permission request write to :-
 * <p>
 * Muhammad Faisal Nadeem.
 * mfaisalnadeem@hotmail.com
 */

@EzTable ( "user" )
public class User {

   @EzPk ( "id" )
   private int id;

   @EzColumn ( "name" )
   private String name;

   @EzColumn ( "email" )
   private String email;

   public User () {
   }

   public int getId () {
      return id;
   }

   public void setId ( int id ) {
      this.id = id;
   }

   public String getName () {
      return name;
   }

   public void setName ( String name ) {
      this.name = name;
   }

   public String getEmail () {
      return email;
   }

   public void setEmail ( String email ) {
      this.email = email;
   }
}
