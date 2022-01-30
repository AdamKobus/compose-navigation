if github.pr_body.length == 0
    fail "Please provide PR description"
end

if git.lines_of_code > 500
    warn "This PR seems big, consider breaking it up into smaller ones"
end

# dependencies check
dangerReport = "build/danger/report.txt"
outdatedDependenciesText = "The following dependencies have later milestone versions"

outdatedDependenciesDetected = File.readlines(dangerReport).grep(/#{outdatedDependenciesText}/).any?

if outdatedDependenciesDetected
  file = File.open(dangerReport, "rb").read
  index = file.index(outdatedDependenciesText)
  message file.slice(index..-1)
end
