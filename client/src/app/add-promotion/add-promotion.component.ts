import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Promotion } from '../dto/promotion';
import { Endpoint } from '../util/endpoints-enum';
import { Global } from '../util/global';
import { Email } from '../dto/email';
import { map } from 'rxjs/operators';
import { User } from '../dto/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-promotion',
  templateUrl: './add-promotion.component.html',
  styleUrls: ['./add-promotion.component.css']
})
export class AddPromotionComponent implements OnInit {


  promotion : Promotion;
  endpoint = Endpoint;


  startDate : Date;
  endDate : Date;
  title : String;
  content : String;

  emailUsers : any



  constructor(private http: HttpClient,  private router: Router) { 
    this.promotion = new Promotion();
    this.promotion.content = "Hristina"
  }



  
  ngOnInit(): void {

    this.promotion.pharmacy = Global.clickedPharmacy;


    ///treba da nadjemo sve user-e koji su pretplaceni na ovu apoteku
    ///promocija se posebno definise za svaku apoteku
    /// Promotion klasa u sebi sadrzi listu pretplacenih korisnika

    const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };
    

    this.http
      .get(this.endpoint.GET_SUBSCRIBED_USERS + Global.clickedPharmacy.id, options)
      .pipe(
        map(returnedUsers=> {
          this.emailUsers = returnedUsers;
        })
      ).subscribe()
  }





  pickStartDate(){


    this.promotion.startDate = this.startDate;

  }

  
  pickEndDate(){


    this.promotion.endDate = this.endDate;

  }

  sendMail(user : User)
  {

    let email: Email = new Email()
    email.recipient = user.email
    email.subject = "New promotion"
    email.message = 'There is new promotion'+ Global.lastPromotion.content + '.'
  }
  

  addPromotion()
  {
     //kreiranje nove promocije
     const headers = { 
      'content-type': 'application/json',
      'Authorization': 'Bearer ' + Global.token.access_token}  
    let options = { headers: headers };

  
    this.promotion.content = this.content;
    this.promotion.startDate = this.startDate;
    this.promotion.endDate = this.endDate;
    this.promotion.title = this.title;

    this.http
      .post(this.endpoint.ADD_PROMOTION, this.promotion, options)
      .pipe().subscribe(returnP => 
      {


          alert("You created new promotion!")

          Global.lastPromotion = this.promotion;

          
          //dobiti sve usere koji su pretplaceni da dobijaju obavestenja 
          

          
          for (let item of this.emailUsers) 
          {
            let email: Email = new Email()
            email.recipient = item.email
            email.subject = "New promotion"
            email.message = 'There is new promotion'+ Global.lastPromotion.content + '.'
           
          
        
            if(confirm("Successfully scheduled appointment. Information were sent to your email address.")) {
            this.http
              .post<any>(this.endpoint.SEND_EMAIL, JSON.stringify(email), options).pipe()
              .subscribe(res => 
                alert("Email sent")
                )
    
            }
          }
      })
  }

}
