import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-footer',
  imports: [],
  templateUrl: './footer.html',
  styleUrl: './footer.css',
})
export class Footer {
  @Output() subscribeClicked = new EventEmitter<void>();

  onSubscribe(){
    this.subscribeClicked.emit();
  }
}
