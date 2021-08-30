import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';
import { Global } from '../util/global';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  private map: L.Map;


  latitude : number = Global.clickedPharmacy.latitude;
  longitude : number = Global.clickedPharmacy.longitude;



  private centroid: L.LatLngExpression = [this.latitude, this.longitude]; //koordinate tacke koju zelimo da prikazemo

  


  constructor() { }

  ngOnInit(): void {

    if (this.latitude == undefined)
      alert("There is no coordinate information about this pharmacy in database!")

    this.initMap();
  }

    //inicijalizacija mape
    private initMap(): void {
      this.map = L.map('map', {
        center: this.centroid,      ///ono sto smo podesili da je centralna tacka
        zoom: 12            //koliko zelimo da uvecamo
      });
  
      const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 18,
        minZoom: 10
      });
  
      
  
      tiles.addTo(this.map);
    
    }



}
