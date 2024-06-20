class IterativeDeepening:
    def __init__(self):
        self.stack = []
        self.number_of_nodes = 0
        self.depth = 0
        self.max_depth = 0
        self.goal_found = False

    def iterative_deepening(self, adjacency_matrix, destination):
        self.number_of_nodes = len(adjacency_matrix) - 1
        while not self.goal_found:
            self.depth_limited_search(adjacency_matrix, 1, destination)
            self.max_depth += 1
        print("\nGoal Found at depth", self.depth)

    def depth_limited_search(self, adjacency_matrix, source, goal):
        element = destination = 1
        visited = [0] * (self.number_of_nodes + 1)
        self.stack.append(source)
        self.depth = 0
        print("\nAt Depth", self.max_depth)
        print(source, end='\t')

        while self.stack:
            element = self.stack[-1]
            while destination <= self.number_of_nodes:
                if self.depth < self.max_depth:
                    if adjacency_matrix[element][destination] == 1:
                        self.stack.append(destination)
                        visited[destination] = 1
                        print(destination, end='\t')
                        self.depth += 1
                        if goal == destination:
                            self.goal_found = True
                            return
                        element = destination
                        destination = 1
                        continue
                destination += 1
            destination = self.stack.pop() + 1
            self.depth -= 1

if __name__ == "__main__":
    try:
        number_of_nodes = int(input("Enter the number of nodes in the graph: "))
        adjacency_matrix = []
        for i in range(number_of_nodes + 1):
            row = []
            for j in range(number_of_nodes + 1):
                row.append(int(input()))  # Assuming input is provided correctly
            adjacency_matrix.append(row)

        destination = int(input("Enter the destination for the graph: "))

        iterative_deepening = IterativeDeepening()
        iterative_deepening.iterative_deepening(adjacency_matrix, destination)
    except ValueError:
        print("Wrong Input format")

'''
0 1 1 0 0 0 0
0 0 0 1 1 0 0
0 0 0 0 0 1 1
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
'''