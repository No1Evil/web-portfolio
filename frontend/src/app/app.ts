import { Component, signal, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Footer} from './shared/components/footer/footer';
import {Navbar} from './shared/components/navbar/navbar';
import {LayoutService} from './core/services/layout/layout-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Footer, Navbar],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  layoutService = inject(LayoutService);

  protected readonly title = signal('frontend');

  handleSubscribe(){
    console.log("all working")
    alert("all working")
  }
}
