package appzspot.sqlitetester.model.dto;

import com.appzspot.easysql.annotations.EzColumn;
import com.appzspot.easysql.annotations.EzFk;
import com.appzspot.easysql.annotations.EzPk;
import com.appzspot.easysql.annotations.EzTable;

/**
 * Created by Muhammad Faisal Nadeem on 10/26/2017.
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

@EzTable ( "item" )
public class Item {

   ///////////////////////////////////////////////////////////////////////////
   // Fields.
   ///////////////////////////////////////////////////////////////////////////

   @EzPk ( "id" )
   private int id;

   @EzColumn ( "name" )
   private String name;

   @EzFk ( name = "user_ref" , references = "user.id")
   private int userRef;

   ///////////////////////////////////////////////////////////////////////////
   // Constructors.
   ///////////////////////////////////////////////////////////////////////////

   public Item () {}

   ///////////////////////////////////////////////////////////////////////////
   // Getters and Setters.
   ///////////////////////////////////////////////////////////////////////////

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

   public int getUserRef () {
      return userRef;
   }

   public void setUserRef ( int userRef ) {
      this.userRef = userRef;
   }


}
