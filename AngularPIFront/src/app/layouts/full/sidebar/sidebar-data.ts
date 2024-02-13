import { NavItem } from './nav-item/nav-item';

export const navItems: NavItem[] = [
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
    iconName: 'rosette',
    route: '/ui-components/badge',
  },
  {
    displayName: 'Company Management',
    iconName: 'poker-chip',
    route: '/ui-components/chips',
  },
  {
    displayName: 'Defense Management',
    iconName: 'list',
    route: '/ui-components/lists',
  },
  {
    displayName: 'Task Management',
    iconName: 'layout-navbar-expand',
    route: '/ui-components/menu',
  },
  {
    displayName: 'Complaint Management',
    iconName: 'tooltip',
    route: '/ui-components/tooltips',
  },
  {
    displayName: 'Internship Management',
    iconName: 'tooltip',
    route: '/ui-components/tooltips',
  },
  {
    displayName: 'Event Management',
    iconName: 'tooltip',
    route: '/ui-components/tooltips',
  },
  {
    displayName: 'Interview Management',
    iconName: 'tooltip',
    route: '/ui-components/tooltips',
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
