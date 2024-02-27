<h2><strong>Final Project M2 Animals World</strong></h2>
<p><strong>Запуск</strong></p>
<p>Є три способи запустити гру:</p>
<ul>
<li>ExecutorService (за замовчуванням)<span class="Apple-converted-space">&nbsp;</span></li>
<li>Через Thread, кожен Thread окремо створений</li>
<li>В одному основному потоці (без використання Thread)</li>
</ul>
<p>В class Main описані 3 запуски. Має бути розкоментований лише один на вибір.<span class="Apple-converted-space">&nbsp;</span></p>
<p>&nbsp;</p>
<p><strong>Створення Animals World і зміни</strong></p>
<p>Программа десеріалізує обʼєкти з файлів YAML. Можна вносити зміни.<span class="Apple-converted-space">&nbsp;</span></p>
<p>Зараз всі обʼєкти створені за таблицями з ТЗ.<span class="Apple-converted-space">&nbsp;</span></p>
<p>&nbsp;</p>
<p><strong>Організми гри:</strong></p>
<p>Створено 5 хижаків</p>
<p>Створено 10 Травоїдних</p>
<p>Створено 1 рослину</p>
<p>&nbsp;</p>
<p><strong>Організми можуть:</strong></p>
<p>Травоїдні і хижаки можуть:</p>
<ul>
<li>їсти рослини та/або інших тварин (якщо в їхній локації є придатна їжа),</li>
<li>пересуватися (до сусідніх локацій),</li>
<li>розмножуватися (якщо в локації наявна пара для них),</li>
<li>вмирати від голоду чи бути з'їденими.</li>
</ul>
<p>Рослини</p>
<ul>
<li>можуть бути зʼїденими</li>
<li>відновлюються після кожного такту гри</li>
</ul>
<p>&nbsp;</p>
<p><strong>Багатопоточність</strong></p>
<p>Використовується багатопотоковий<span class="Apple-converted-space">&nbsp; </span>random <strong><em>ThreadLocalRandom.current().nextInt()</em></strong></p>
<p>&nbsp;</p>
<p><strong>Статистика</strong></p>
<p>Після кожного етапу гри в консоль виводиться інформація про кількість Організмів в клітинці.<span class="Apple-converted-space">&nbsp;</span></p>
<p>Також виводиться інформація скільки Організмів померло, скільки було створено нових і скільки рослин відновилось.</p>
<p>&nbsp;</p>
<p><strong>Кінець гри</strong></p>
<p>Умова кінця гри: залишились живими лише рослини.<span class="Apple-converted-space">&nbsp;</span></p>
<p>Приблизно, кінець гри наступає через 30-40 тактів. (При налаштуваннях за замовчуванням)</p>
