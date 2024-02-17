import time


start_time = time.perf_counter()
for i in range(100):
    import Lab4Main
end_time = time.perf_counter()

print(f"Основа - {end_time - start_time}")

start_time = time.perf_counter()
for i in range(100):
    import dop1
end_time = time.perf_counter()

print(f"Доп 1  - {end_time - start_time}")

start_time = time.perf_counter()
for i in range(100):
    import dop2
end_time = time.perf_counter()

print(f"Доп 2  - {end_time - start_time}")

start_time = time.perf_counter()
for i in range(100):
    import dop3
end_time = time.perf_counter()

print(f"Доп 3  - {end_time - start_time}")
