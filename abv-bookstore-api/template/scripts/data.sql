
INSERT INTO authors (name, bio) VALUES
                                   ('Max Lucado', 'Max Lucado is a pastor and best-selling author known for inspirational books such as "You’ll Get Through This" and "Anxious for Nothing."'),
                                   ('Priscilla Shirer', 'Priscilla Shirer is a Bible teacher, author, and actress known for her work on prayer and women’s ministry, including the book "Fervent."'),
                                   ('C.S. Lewis', 'C.S. Lewis was a British author, theologian, and apologist, best known for "Mere Christianity" and "The Chronicles of Narnia."'),
                                   ('John Piper', 'John Piper is a Reformed theologian and pastor, well known for "Desiring God" and his teachings on Christian hedonism.'),
                                   ('Stormie Omartian', 'Stormie Omartian is an author and speaker best known for her "Power of a Praying..." series focusing on prayer and spiritual growth.');

INSERT INTO authors (name, bio) VALUES
                                   ('Francine Rivers', 'Francine Rivers is a best-selling author of Christian fiction, known for her powerful storytelling in books like "Redeeming Love."'),
                                   ('Tony Evans', 'Dr. Tony Evans is a pastor, teacher, and speaker, widely respected for his work in theology and biblical teaching.'),
                                   ('Joyce Meyer', 'Joyce Meyer is a well-known Bible teacher and author, recognized for her practical teachings and books like "Battlefield of the Mind."'),
                                   ('Tim Keller', 'Tim Keller was a pastor and theologian, best known for "The Reason for God" and founding Redeemer Presbyterian Church in New York City.'),
                                   ('Beth Moore', 'Beth Moore is a Christian evangelist and author, known for her Bible studies and books on women’s ministry.'),
                                   ('David Platt', 'David Platt is a pastor and author of "Radical," a book challenging Christians to a life of full devotion to Christ.'),
                                   ('Lisa Bevere', 'Lisa Bevere is a speaker and author passionate about women’s identity in Christ, known for books like "Without Rival."'),
                                   ('Andy Stanley', 'Andy Stanley is a pastor and author of numerous books on leadership and church ministry.'),
                                   ('Ravi Zacharias', 'Ravi Zacharias was a Christian apologist and speaker, known for his global ministry and books on defending the Christian faith.'),
                                   ('Lysa TerKeurst', 'Lysa TerKeurst is a speaker and author who writes about faith, relationships, and emotional healing, known for "It’s Not Supposed to Be This Way."');

INSERT INTO books (title, isbn, publisher, author, price, stock_quantity, category_id, author_id) VALUES
('Mere Christianity', '9780060652920', 'HarperOne', 'C.S. Lewis', 14.99, 25, 12, 3),
('Fervent', '9781433688676', 'B&H Books', 'Priscilla Shirer', 16.99, 18, 9, 2),
('Redeeming Love', '9781601420619', 'Multnomah', 'Francine Rivers', 13.99, 40, 8, 6),
('The Power of a Praying Wife', '9780736919241', 'Harvest House Publishers', 'Stormie Omartian', 10.99, 30, 9, 5),
('The Reason for God', '9781594483493', 'Penguin Books', 'Tim Keller', 17.99, 22, 14, 9),
('Desiring God', '9781601423108', 'Multnomah', 'John Piper', 15.49, 12, 12, 4),
('It’s Not Supposed to Be This Way', '9780718039851', 'Thomas Nelson', 'Lysa TerKeurst', 18.00, 35, 10, 15),
('Battlefield of the Mind', '9780446691093', 'FaithWords', 'Joyce Meyer', 13.49, 27, 7, 8),
('Radical', '9781601422217', 'Multnomah', 'David Platt', 11.99, 20, 14, 11),
('Without Rival', '9780800727246', 'Revell', 'Lisa Bevere', 14.00, 15, 10, 12);

INSERT INTO books (title, isbn, publisher, author, price, stock_quantity, category_id, author_id) VALUES
('Anxious for Nothing', '9780718096120', 'Thomas Nelson', 'Max Lucado', 15.99, 28, 11, 1),
('The Purpose Driven Life', '9780310337508', 'Zondervan', 'Rick Warren', 16.99, 35, 13, 13),
('The Screwtape Letters', '9780060652937', 'HarperOne', 'C.S. Lewis', 12.49, 20, 18, 3),
('Kingdom Man', '9781589976856', 'Focus on the Family', 'Tony Evans', 14.00, 25, 14, 7),
('Jesus Calling', '9781591451884', 'Thomas Nelson', 'Sarah Young', 10.99, 30, 8, 10),
('Lioness Arising', '9780307457790', 'WaterBrook', 'Lisa Bevere', 13.99, 17, 10, 12),
('The Prodigal God', '9781594484025', 'Penguin Books', 'Tim Keller', 14.99, 19, 12, 9),
('The Power of a Praying Parent', '9780736957700', 'Harvest House Publishers', 'Stormie Omartian', 11.99, 24, 9, 5),
('Forgiving What You Can’t Forget', '9780718039875', 'Thomas Nelson', 'Lysa TerKeurst', 18.99, 22, 10, 15),
('God Is Not Mad at You', '9781455517479', 'FaithWords', 'Joyce Meyer', 13.49, 26, 7, 8);

INSERT INTO book (title, isbn, publisher, author, price, stock_quantity, category_id, author_id) VALUES
('Crazy Love', '9781434705945', 'David C. Cook', 'Francis Chan', 13.99, 32, 14, 14),
('Knowing God', '9780830831040', 'IVP Books', 'J.I. Packer', 15.99, 18, 12, 3),
('Uninvited', '9781400205875', 'Thomas Nelson', 'Lysa TerKeurst', 16.00, 21, 10, 15),
('The Case for Christ', '9780310339304', 'Zondervan', 'Lee Strobel', 14.99, 29, 15, 4),
('Discerning the Voice of God', '9780802450128', 'Moody Publishers', 'Priscilla Shirer', 13.99, 25, 9, 2),
('Let Me Be a Woman', '9780842321624', 'Tyndale House Publishers', 'Elisabeth Elliot', 12.99, 17, 10, 13),
('Waking the Dead', '9780785262195', 'Thomas Nelson', 'John Eldredge', 14.49, 20, 13, 6),
('The Knowledge of the Holy', '9780060684129', 'HarperOne', 'A.W. Tozer', 11.99, 22, 12, 7),
('The Power of a Praying Husband', '9780736919746', 'Harvest House Publishers', 'Stormie Omartian', 10.99, 26, 9, 5),
('Captivating', '9781400280834', 'Thomas Nelson', 'Stasi Eldredge', 14.00, 24, 10, 12);


-- mapping book_author

INSERT INTO book_author (book_id, author_id) VALUES (1, 12);
INSERT INTO book_author (book_id, author_id) VALUES (1, 15);
INSERT INTO book_author (book_id, author_id) VALUES (1, 14);
INSERT INTO book_author (book_id, author_id) VALUES (2, 5);
INSERT INTO book_author (book_id, author_id) VALUES (2, 10);
INSERT INTO book_author (book_id, author_id) VALUES (2, 4);
INSERT INTO book_author (book_id, author_id) VALUES (3, 7);
INSERT INTO book_author (book_id, author_id) VALUES (4, 2);
INSERT INTO book_author (book_id, author_id) VALUES (4, 9);
INSERT INTO book_author (book_id, author_id) VALUES (5, 7);
INSERT INTO book_author (book_id, author_id) VALUES (5, 4);
INSERT INTO book_author (book_id, author_id) VALUES (5, 14);
INSERT INTO book_author (book_id, author_id) VALUES (6, 6);
INSERT INTO book_author (book_id, author_id) VALUES (6, 15);
INSERT INTO book_author (book_id, author_id) VALUES (6, 10);
INSERT INTO book_author (book_id, author_id) VALUES (7, 13);
INSERT INTO book_author (book_id, author_id) VALUES (7, 6);
INSERT INTO book_author (book_id, author_id) VALUES (8, 15);
INSERT INTO book_author (book_id, author_id) VALUES (8, 8);
INSERT INTO book_author (book_id, author_id) VALUES (8, 6);
INSERT INTO book_author (book_id, author_id) VALUES (9, 8);
INSERT INTO book_author (book_id, author_id) VALUES (10, 9);
INSERT INTO book_author (book_id, author_id) VALUES (10, 12);
INSERT INTO book_author (book_id, author_id) VALUES (10, 1);
INSERT INTO book_author (book_id, author_id) VALUES (11, 3);
INSERT INTO book_author (book_id, author_id) VALUES (11, 7);
INSERT INTO book_author (book_id, author_id) VALUES (11, 5);
INSERT INTO book_author (book_id, author_id) VALUES (12, 4);
INSERT INTO book_author (book_id, author_id) VALUES (12, 10);
INSERT INTO book_author (book_id, author_id) VALUES (12, 6);
INSERT INTO book_author (book_id, author_id) VALUES (13, 6);
INSERT INTO book_author (book_id, author_id) VALUES (14, 2);
INSERT INTO book_author (book_id, author_id) VALUES (15, 12);
INSERT INTO book_author (book_id, author_id) VALUES (15, 5);
INSERT INTO book_author (book_id, author_id) VALUES (16, 11);
INSERT INTO book_author (book_id, author_id) VALUES (17, 11);
INSERT INTO book_author (book_id, author_id) VALUES (17, 10);
INSERT INTO book_author (book_id, author_id) VALUES (18, 14);
INSERT INTO book_author (book_id, author_id) VALUES (18, 7);
INSERT INTO book_author (book_id, author_id) VALUES (18, 8);
INSERT INTO book_author (book_id, author_id) VALUES (19, 6);
INSERT INTO book_author (book_id, author_id) VALUES (19, 12);
INSERT INTO book_author (book_id, author_id) VALUES (19, 7);
INSERT INTO book_author (book_id, author_id) VALUES (20, 12);
INSERT INTO book_author (book_id, author_id) VALUES (20, 3);
INSERT INTO book_author (book_id, author_id) VALUES (21, 6);
INSERT INTO book_author (book_id, author_id) VALUES (22, 9);
INSERT INTO book_author (book_id, author_id) VALUES (22, 3);
INSERT INTO book_author (book_id, author_id) VALUES (22, 15);
INSERT INTO book_author (book_id, author_id) VALUES (23, 2);
INSERT INTO book_author (book_id, author_id) VALUES (23, 11);
INSERT INTO book_author (book_id, author_id) VALUES (24, 1);
INSERT INTO book_author (book_id, author_id) VALUES (25, 13);
INSERT INTO book_author (book_id, author_id) VALUES (25, 4);
INSERT INTO book_author (book_id, author_id) VALUES (25, 12);
INSERT INTO book_author (book_id, author_id) VALUES (26, 3);
INSERT INTO book_author (book_id, author_id) VALUES (26, 15);
INSERT INTO book_author (book_id, author_id) VALUES (26, 7);
INSERT INTO book_author (book_id, author_id) VALUES (27, 14);
INSERT INTO book_author (book_id, author_id) VALUES (27, 8);
INSERT INTO book_author (book_id, author_id) VALUES (27, 2);
INSERT INTO book_author (book_id, author_id) VALUES (28, 1);
INSERT INTO book_author (book_id, author_id) VALUES (29, 7);
INSERT INTO book_author (book_id, author_id) VALUES (29, 14);
INSERT INTO book_author (book_id, author_id) VALUES (30, 7);
INSERT INTO book_author (book_id, author_id) VALUES (30, 15);





-- Order 1: Walk-in Purchase
INSERT INTO orders (
  customer_name,
  customer_phone,
  order_type,
  status,
  total_amount
) VALUES (
  'John Doe',
  '09123456789',
  'walk-in',
  'completed',
  45.50
);

-- -- Order 2: Online Purchase (Pending Payment)
INSERT INTO orders (
  customer_name,
  customer_email,
  customer_phone,
  order_type,
  status,
  total_amount,
  shipping_address
) VALUES (
  'Jane Smith',
  'jane@example.com',
  '09998887777',
  'online',
  'pending',
  89.99,
  '123 Main Street, Yangon, Myanmar'
);

-- -- Order 3: Walk-in Purchase (Pending Confirmation)
INSERT INTO orders (
  customer_name,
  customer_phone,
  order_type,
  status,
  total_amount
) VALUES (
  'Aung Aung',
  '09555512345',
  'walk-in',
  'pending',
  23.00
);
INSERT INTO orders (
    customer_name,
    customer_phone,
    order_type,
    total_amount,
    status
)
VALUES (
    'Alice Tun',
    '09123456789',
    'walk-in',
    15000.00,
    'completed'
);

INSERT INTO orders (
    customer_name,
    customer_email,
    customer_phone,
    order_type,
    shipping_address,
    total_amount,
    status
)
VALUES (
    'Myo Ko Ko',
    'myo.koko@example.com',
    '09765432100',
    'online',
    'No.123, Insein Road, Yangon',
    27500.00,
    'pending'
);

INSERT INTO orders (
    customer_name,
    customer_phone,
    order_type,
    total_amount,
    status
)
VALUES (
    'Daw Hla Hla',
    '09998887766',
    'walk-in',
    8900.00,
    'completed'
);


INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (2, 17, 2, 7500.00, 15000.00);
INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (5, 9, 1, 12500.00, 12500.00);
INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (1, 22, 3, 6500.00, 19500.00);
INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (3, 11, 2, 9800.00, 19600.00);
INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (6, 4, 1, 15000.00, 15000.00);
INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (4, 28, 4, 5200.00, 20800.00);
INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (2, 7, 2, 8300.00, 16600.00);
INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (1, 13, 1, 9900.00, 9900.00);
INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (3, 30, 5, 5600.00, 28000.00);
INSERT INTO order_items (order_id, book_id, quantity, price, total) VALUES (5, 19, 2, 14200.00, 28400.00);



INSERT INTO category (name, description) VALUES
                                             ('Biographies', 'Stories of influential Christian figures and missionaries.'),
                                             ('Theology', 'Books focused on theological studies and doctrines.'),
                                             ('Church Leadership', 'Resources for pastors, elders, and ministry leaders.'),
                                             ('Prayer', 'Books and guides on developing a deeper prayer life.'),
                                             ('Marriage & Family', 'Christian perspectives on relationships, marriage, and parenting.'),
                                             ('Apologetics', 'Defending the faith with reason and evidence-based works.'),
                                             ('Worship & Music', 'Resources on worship leading, hymnals, and Christian music.'),
                                             ('Spiritual Warfare', 'Books on spiritual battles and biblical teachings on the topic.'),
                                             ('Christian Fiction', 'Faith-based novels and inspirational fiction stories.'),
                                             ('Prophecy & End Times', 'Books exploring biblical prophecy and eschatology.');


INSERT INTO category (name, description) VALUES
                                             ('Bibles', 'Various translations and editions of the Holy Bible.'),
                                             ('Devotionals', 'Daily and weekly devotional books for spiritual growth.'),
                                             ('Christian Living', 'Books on how to live a Christ-centered life.'),
                                             ('Prayer', 'Books and guides on developing a deeper prayer life.'),
                                             ('Apologetics', 'Defending the faith with reason and evidence-based works.');

INSERT INTO category (name, description) VALUES
                                             ('Children & Teens', 'Faith-based books for children and teenagers.'),
                                             ('Bible Stories for Kids', 'Illustrated Bible stories for young readers.'),
                                             ('Teen Devotionals', 'Daily devotions and life guidance for teens.');


INSERT INTO category (name, description) VALUES
                                             ('Women''s Devotionals', 'Encouraging devotionals specifically for women.'),
                                             ('Christian Women', 'Faith-based books for women on purpose, identity, and calling.');

INSERT INTO category (name, description) VALUES
                                             ('Christian Men', 'Books focused on spiritual growth and leadership for men.'),
                                             ('Fatherhood & Leadership', 'Guides for men on biblical fatherhood and godly leadership.');


INSERT INTO category (name, description) VALUES
                                             ('Church Leadership', 'Resources for pastors, elders, and ministry leaders.'),
                                             ('Study Guides', 'Bible study guides and workbooks for group or individual use.'),
                                             ('Theology', 'Books focused on theological studies and doctrines.');





INSERT INTO invoices (
    order_id,
    invoice_number,
    payment_method,
    total_amount,
    status
)
VALUES
    (3, 'INV-20250412-001', 'cash', 15000.00, 'paid');

INSERT INTO invoices (
    order_id,
    invoice_number,
    payment_method,
    total_amount,
    status
)
VALUES
    (1, 'INV-20250412-002', 'credit_card', 27500.00, 'paid');

INSERT INTO invoices (
    order_id,
    invoice_number,
    payment_method,
    total_amount,
    status
)
VALUES
    (5, 'INV-20250412-003', 'e_wallet', 8900.00, 'unpaid');

INSERT INTO invoices (
    order_id,
    invoice_number,
    payment_method,
    total_amount,
    status
)
VALUES
    (2, 'INV-20250412-004', 'cash', 20800.00, 'paid');

INSERT INTO invoices (
    order_id,
    invoice_number,
    payment_method,
    total_amount,
    status
)
VALUES
    (6, 'INV-20250412-005', 'credit_card', 32000.00, 'paid');





INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (2, 14, 2, 7500.00, 15000.00);
INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (4, 7, 1, 13200.00, 13200.00);
INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (1, 21, 3, 5800.00, 17400.00);
INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (5, 29, 2, 9900.00, 19800.00);
INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (3, 3, 4, 6500.00, 26000.00);
INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (1, 17, 2, 8700.00, 17400.00);
INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (4, 11, 1, 14300.00, 14300.00);
INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (2, 6, 3, 7200.00, 21600.00);
INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (5, 19, 1, 16500.00, 16500.00);
INSERT INTO invoice_items (invoice_id, book_id, quantity, price, total) VALUES (3, 24, 2, 9500.00, 19000.00);
