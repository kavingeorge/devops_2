# Video Game E-Commerce System Implementation Plan

## 1. Database & Entities
- [x] Rename and update login.java to User.java with roles (USER, SELLER, ADMIN)
- [x] Update product.java to Game.java (add approval status, release date, genre, description, download link)
- [x] Rename supplier.java to Developer.java (add role SELLER)
- [x] Update bill.java to Order.java (add order date, status, total)
- [x] Create OrderItem.java (links order to games with quantity, price)
- [x] Create Library.java (user's purchased games with download access)
- [x] Create Refund.java (refund requests with status)
- [x] Add enums: UserRole, ApprovalStatus, OrderStatus, RefundStatus

## 2. Authentication & Roles
- [x] Update LogService and LogRepo for role-based authentication
- [x] Modify LogController for role handling
- [x] Update RegController to assign roles (default USER, option for SELLER)
- [x] Add role checks in controllers

## 3. Game Management
- [x] Rename ProdController to GameController
- [x] Update ProService to GameService
- [x] Update ProRepo to GameRepo
- [x] Add approval workflow (SELLER submits, ADMIN approves)
- [x] Add release scheduling (games visible only after release date)

## 4. Order & Payment
- [x] Rename BillController to OrderController
- [x] Update BillService to OrderService
- [x] Update BillRepo to OrderRepo
- [x] Add mock payment processing
- [x] Generate receipts

## 5. User Library & Delivery
- [ ] Create LibraryController
- [ ] Create LibraryService and LibraryRepo
- [ ] Add download links to library
- [ ] Restrict access based on purchase

## 6. Search & Catalog
- [ ] Add search functionality in GameController
- [ ] Implement filters (genre, price, release date)
- [ ] Update catalog templates

## 7. Release Scheduling
- [ ] Update MyTaskScheduler for game releases
- [ ] Scheduled task to make games available on release date

## 8. Refunds
- [ ] Create RefundController
- [ ] Create RefundService and RefundRepo
- [ ] Add refund request/approval workflow
- [ ] Revoke library access on refund

## 9. Frontend
- [ ] Update all Thymeleaf templates for video game theme
- [ ] Add new templates for library, refunds, approvals
- [ ] Update navigation and styling

## 10. Configuration
- [ ] Update pom.xml for any new dependencies
- [ ] Update application.properties if needed
- [ ] Update MySQL schema scripts

## Followup Steps
- [ ] Test user registration and login with roles
- [ ] Test game CRUD and approval workflow
- [ ] Test search and catalog browsing
- [ ] Test purchasing and payment mock
- [ ] Test library and downloads
- [ ] Test refunds
- [ ] Test release scheduling
