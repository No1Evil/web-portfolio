import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from '@shared/components/navbar/navbar';
import { Footer } from '@shared/components/footer/footer';

@Component({
  selector: 'app-public-shell',
  imports: [RouterOutlet, Navbar, Footer],
  templateUrl: './public-shell.html',
  styleUrl: './public-shell.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PublicShell {}