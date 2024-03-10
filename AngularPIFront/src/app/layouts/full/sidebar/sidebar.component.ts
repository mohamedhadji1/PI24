import { Component, OnInit } from '@angular/core';
import { NavItem } from './nav-item/nav-item';
import { AuthService } from '../../../services/auth.service';
import { Role } from '../../../core/User'; // Import the Role enum

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
})
export class SidebarComponent implements OnInit {
  navItems: NavItem[] = [];

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    // Get current user
    const currentUser = this.authService.getCurrentUser();

    // Set navItems based on user's role
    if (currentUser && currentUser.role === Role.SUPERVISOR) {
      this.navItems = [
        {
          displayName: 'Dashboard',
          iconName: 'layout-dashboard',
          route: '/dashboard',
        },
        {
          displayName: 'Company Management',
          iconName: 'briefcase',
          route: '/ui-components/company',
        },
        {
          displayName: 'Interview Management',
          iconName: 'video',
          route: '/ui-components/interview',
        },
        {
          displayName: 'Task Management',
          iconName: 'checklist',
          route: '/ui-components/task',
        },
        {
          navCap: 'Chat',
        },
        {
          displayName: 'chat coaching',
          iconName: 'checklist',
          route: '/ui-components/chat',
        },
        {
          navCap: 'Auth',
        },
        {
          displayName: 'Login',
          iconName: 'lock',
          route: '/authentication/login',
        },
      ];
    }
    // Add additional conditions for other roles if needed
    if (currentUser && currentUser.role === Role.STUDENT) {
      this.navItems = [
        {
          displayName: 'Dashboard',
          iconName: 'layout-dashboard',
          route: '/dashboard',
        },
        {
          displayName: 'Interview Management',
          iconName: 'video',
          route: '/ui-components/interview',
        },
        {
          displayName: 'Documents Managements',
          iconName: 'file-type-doc',
          route: '/ui-components/documents',
        },
        {
          displayName: 'Task Management',
          iconName: 'checklist',
          route: '/ui-components/task',
        },
        {
          displayName: 'Turn in tasks',
          iconName: 'checklist',
          route: '/ui-components/taskstudent',
        },
        {
          navCap: 'Chat',
        },
        {
          displayName: 'chat coaching',
          iconName: 'checklist',
          route: '/ui-components/chat',
        },
        {
          navCap: 'Auth',
        },
        {
          displayName: 'Login',
          iconName: 'lock',
          route: '/authentication/login',
        },

      ];
    }
    if (currentUser && currentUser.role === Role.ADMIN) {
      this.navItems = [
        {
          navCap: 'Home',
        },
        {
          displayName: 'Dashboard',
          iconName: 'layout-dashboard',
          route: '/dashboard',
        },
        {
          navCap: 'ESPRIT Internship',
        },
        {
          displayName: 'User Management',
          iconName: 'users',
          route: '/ui-components/user',
        },
        {
          displayName: 'Company Management',
          iconName: 'briefcase',
          route: '/ui-components/company',
        },
        {
          displayName: 'Defense Management',
          iconName: 'gavel',
          route: '/ui-components/defense',
        },
        {
          displayName: 'Task Management',
          iconName: 'checklist',
          route: '/ui-components/task',
        },
        {
          displayName: 'Complaint Management',
          iconName: 'tooltip',
          route: '/ui-components/complaint',
        },
        {
          displayName: 'Internship Management',
          iconName: 'file-description',
          route: '/ui-components/internship',
        },
        {
          displayName: 'Event Management',
          iconName: 'calendar-event',
          route: '/ui-components/event',
        },
        {
          displayName: 'Interview Management',
          iconName: 'video',
          route: '/ui-components/interview',
        },
        {
          displayName: 'Documents Managements',
          iconName: 'file-type-doc',
          route: '/ui-components/documents',
        },
        {
          displayName: 'Login',
          iconName: 'lock',
          route: '/authentication/login',
        },

      ];
    }
  }
}
